package com.csencan.sepetim.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.csencan.sepetim.models.base.BaseResponse
import com.csencan.sepetim.models.register.RegisterRequestModel
import com.csencan.sepetim.utils.RestApiServices
import com.google.gson.Gson
import kotlinx.coroutines.launch

class LoginActivityVM : ViewModel() {
    private lateinit var email: String
    private lateinit var password: String
    val loginResponse : MutableLiveData<BaseResponse<*>> = MutableLiveData()

    fun signUp(email : String, password : String) {
        this.email = email
        this.password = password
        val loggedInUser = RegisterRequestModel(email,password)

        viewModelScope.launch {
            try {
                val response = RestApiServices.loginUser(loggedInUser)
                if(response.isSuccessful) {
                    val successfulResponse = response.body()
                    if(successfulResponse != null) {
                        loginResponse.value = successfulResponse
                    }
                } else {
                    val unsuccessfulResponse = Gson().fromJson(response.errorBody()?.charStream()?.readText(), BaseResponse::class.java)
                    if(unsuccessfulResponse != null) {
                        loginResponse.value = unsuccessfulResponse
                    }
                }
            } catch (exception : Exception) {
                println(exception.message) //TODO Crashlytics
            }

        }
    }

}