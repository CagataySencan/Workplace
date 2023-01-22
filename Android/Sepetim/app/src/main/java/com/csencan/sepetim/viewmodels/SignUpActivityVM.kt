package com.csencan.sepetim.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SignUpActivityVM : ViewModel() {
    private lateinit var email: String
    private lateinit var password: String
    private lateinit var confirmPassword: String

    fun signUp(email : String, password : String, confirmPassword : String) {
        this.email = email
        this.password = password
        this.confirmPassword = confirmPassword
    }

}