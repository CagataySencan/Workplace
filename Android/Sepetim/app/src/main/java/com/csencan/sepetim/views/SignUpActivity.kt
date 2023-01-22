package com.csencan.sepetim.views

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.widget.AppCompatButton
import androidx.lifecycle.ViewModelProvider
import com.csencan.sepetim.R
import com.csencan.sepetim.viewmodels.SignUpActivityVM

class SignUpActivity : AppCompatActivity() {
    private lateinit var signUpVM : SignUpActivityVM
    private lateinit var emailEditText: EditText
    private lateinit var passwordEditText: EditText
    private lateinit var confirmPasswordEditText: EditText
    private lateinit var emailAlertTextView : TextView
    private lateinit var passwordAlertTextView : TextView
    private lateinit var confirmPasswordAlertTextView : TextView
    private lateinit var signUpButton : AppCompatButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)
        initFields()
        initOnclickListeners()
    }

    private fun initFields() {
        signUpVM = ViewModelProvider(this)[SignUpActivityVM::class.java]
        emailEditText = findViewById(R.id.sign_up_email_et)
        passwordEditText = findViewById(R.id.signup_password_et)
        confirmPasswordEditText = findViewById(R.id.signup_password_again_et)
        emailAlertTextView = findViewById(R.id.signup_email_alert_tv)
        passwordAlertTextView = findViewById(R.id.signup_password_alert_tv)
        confirmPasswordAlertTextView = findViewById(R.id.signup_password_again_alert_tv)
        signUpButton = findViewById(R.id.sign_up_button)
    }

    private fun initOnclickListeners() {
        signUpButton.setOnClickListener {
            checkFieldsAndSignUp()
        }
    }

    private fun checkFieldsAndSignUp() {
        handleAlertTextViews()

        if(emailEditText.text.isNullOrEmpty()
            || passwordEditText.text.isNullOrEmpty()
            || confirmPasswordEditText.text.isNullOrEmpty()
            || (passwordEditText.text.toString() !=  confirmPasswordEditText.text.toString()))
        {
            return
        }
        else {
            signUpVM.signUp(emailEditText.text.toString(), passwordEditText.text.toString(), confirmPasswordEditText.text.toString())
        }

    }

    private fun handleAlertTextViews() {
        setAlertTextViews(null,null)

        if (passwordEditText.text.toString() !=  confirmPasswordEditText.text.toString()) {
            setAlertTextViews(confirmPasswordAlertTextView, R.string.sign_up_password_match_alert)
            setAlertTextViews(passwordAlertTextView, R.string.sign_up_password_match_alert)
        }

        if(emailEditText.text.isNullOrEmpty()) {
            setAlertTextViews(emailAlertTextView, R.string.sign_up_email_alert)
        }

        if(passwordEditText.text.isNullOrEmpty()) {
            setAlertTextViews(passwordAlertTextView, R.string.sign_up_password_alert)
        }

        if (confirmPasswordEditText.text.isNullOrEmpty()) {
            setAlertTextViews(confirmPasswordAlertTextView, R.string.sign_up_confirm_password_alert)
        }

    }

    private fun setAlertTextViews(textView: TextView?, alert : Int?) {
        if(textView == null && alert == null) {
            emailAlertTextView.visibility = View.GONE
            passwordAlertTextView.visibility = View.GONE
            confirmPasswordAlertTextView.visibility = View.GONE
            return
        }

        textView?.visibility = View.VISIBLE
        textView?.text = " " + getString(alert!!)
    }
}