package com.csencan.sepetim.views

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.widget.AppCompatButton
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.csencan.sepetim.R
import com.csencan.sepetim.viewmodels.LoginActivityVM

class LoginActivity : AppCompatActivity() {
    private lateinit var loginButton : AppCompatButton
    private lateinit var signUpButton : AppCompatButton
    private lateinit var loginVM : LoginActivityVM
    private lateinit var eMailEditText : EditText
    private lateinit var passwordEditText: EditText
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        loginVM = ViewModelProvider(this)[LoginActivityVM::class.java]

        loginButton = findViewById(R.id.login_button)
        signUpButton = findViewById(R.id.login_sign_up_button)



        signUpButton.setOnClickListener {
            signUp()
        }
    }

    private fun signUp() {
        val intent = Intent(this@LoginActivity,SignUpActivity::class.java)
        startActivity(intent)
    }

    fun login() {
        // login
    }
}