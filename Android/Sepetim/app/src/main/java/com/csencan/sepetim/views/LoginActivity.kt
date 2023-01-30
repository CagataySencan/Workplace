package com.csencan.sepetim.views

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.widget.AppCompatButton
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.csencan.sepetim.R
import com.csencan.sepetim.models.base.BaseResponse
import com.csencan.sepetim.models.base.User
import com.csencan.sepetim.models.register.LoginResponseModel
import com.csencan.sepetim.models.register.RegisterResponseModel
import com.csencan.sepetim.utils.Util
import com.csencan.sepetim.viewmodels.LoginActivityVM
import com.csencan.sepetim.viewmodels.SignUpActivityVM
import com.google.gson.Gson
import com.tdonuk.util.text.StringUtils

class LoginActivity : AppCompatActivity() {
    private lateinit var loginVM : LoginActivityVM
    private lateinit var loginButton : AppCompatButton
    private lateinit var signUpButton : AppCompatButton
    private lateinit var emailEditText : EditText
    private lateinit var passwordEditText: EditText
    private lateinit var emailAlertTextView : TextView
    private lateinit var passwordAlertTextView : TextView
    private lateinit var forgotPasswordTextView : TextView
    private var loginResponseModel : LoginResponseModel = LoginResponseModel()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        initFields()
        initOnclickListeners()
        observeLiveData()
    }

    private fun initFields() {
        loginVM = ViewModelProvider(this)[LoginActivityVM::class.java]
        loginButton = findViewById(R.id.login_button)
        signUpButton = findViewById(R.id.login_sign_up_button)
        emailEditText = findViewById(R.id.login_email_et)
        passwordEditText = findViewById(R.id.login_password_et)
        emailAlertTextView = findViewById(R.id.login_email_alert_tv)
        passwordAlertTextView = findViewById(R.id.login_password_alert_tv)
        forgotPasswordTextView = findViewById(R.id.forgot_password_tv)
    }

    private fun initOnclickListeners() {
        signUpButton.setOnClickListener {
            signUp()
        }

        loginButton.setOnClickListener {
            checkFieldsAndLogin()
        }
    }
    private fun checkFieldsAndLogin() {
        handleAlertTextViews()

        if(emailEditText.text.isNullOrEmpty() || passwordEditText.text.isNullOrEmpty()) {
            return
        }
        else {
            loginVM.signUp(emailEditText.text.toString(), passwordEditText.text.toString())
        }

    }

    private fun signUp() {
        val intent = Intent(this@LoginActivity,SignUpActivity::class.java)
        startActivity(intent)
    }

    private fun handleAlertTextViews() {
        setAlertTextViews(null,null)

        if(emailEditText.text.isNullOrEmpty()) {
            setAlertTextViews(emailAlertTextView, R.string.sign_up_email_alert)
        }

        if(passwordEditText.text.isNullOrEmpty()) {
            setAlertTextViews(passwordAlertTextView, R.string.sign_up_password_alert)
        }
    }

    private fun setAlertTextViews(textView: TextView?, alert : Int?) {
        if(textView == null && alert == null) {
            emailAlertTextView.visibility = View.GONE
            passwordAlertTextView.visibility = View.GONE
            return
        }

        textView?.visibility = View.VISIBLE
        textView?.text = " " + getString(alert!!)
    }

    private fun observeLiveData() {
        loginVM.loginResponse.observe(this) {response ->
            setResponseModel(response,loginResponseModel)
            if (loginResponseModel.fault != null) {
                val alertDialog = Util.createUnsuccessfullResponseDialog(
                    this@LoginActivity,
                    loginResponseModel.fault?.shortDes,
                    loginResponseModel.fault?.longDes
                )
                alertDialog.show()
            }

            else if(loginResponseModel.message != null) {
                // TODO message sharedprefe kaydedilip uygulamaya login olunacak
            }
        }
    }

    private fun setResponseModel(response : BaseResponse<*>, loginResponseModel: LoginResponseModel) {
        val message = Gson().toJson(response.data)
        val fault = response.fault
        val status = response.status

        loginResponseModel.message = message
        loginResponseModel.fault = fault
        loginResponseModel.status = status
    }
}