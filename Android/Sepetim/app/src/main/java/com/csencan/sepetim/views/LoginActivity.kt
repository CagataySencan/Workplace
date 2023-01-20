package com.csencan.sepetim.views

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.widget.AppCompatButton
import com.csencan.sepetim.R

class LoginActivity : AppCompatActivity() {
    private lateinit var loginButton : AppCompatButton
    private lateinit var signUpButton : AppCompatButton
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        loginButton = findViewById(R.id.login_button)
        signUpButton = findViewById(R.id.login_sign_up_button)

        signUpButton.setOnClickListener {
            signUp()
        }
    }

    fun signUp() {
        val intent = Intent(this@LoginActivity,SignUpActivity::class.java)
        startActivity(intent)
    }
}