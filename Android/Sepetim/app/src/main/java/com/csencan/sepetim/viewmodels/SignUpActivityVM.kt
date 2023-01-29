package com.csencan.sepetim.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.csencan.sepetim.models.base.BaseFault
import com.csencan.sepetim.models.base.BaseResponse
import com.csencan.sepetim.models.base.User
import com.csencan.sepetim.models.register.RegisterRequestModel
import com.csencan.sepetim.models.register.RegisterResponseModel
import com.csencan.sepetim.utils.RestApiServices
import com.csencan.sepetim.utils.ServiceClient
import com.google.gson.Gson
import kotlinx.coroutines.launch

class SignUpActivityVM() : ViewModel() {
    private lateinit var email: String
    private lateinit var password: String
    private lateinit var confirmPassword: String
    val registerResponse : MutableLiveData<BaseResponse<*>> = MutableLiveData()

    fun signUp(email : String, password : String, confirmPassword : String) {
        this.email = email
        this.password = password
        this.confirmPassword = confirmPassword
        val newUser = RegisterRequestModel(email,password)

        viewModelScope.launch {
            val response = RestApiServices.addUser(newUser)

            if(response.isSuccessful) {
                val successfulResponse = response.body()
                if(successfulResponse != null) {
                    registerResponse.value = successfulResponse
                }
            } else {
                val unsuccessfulResponse = Gson().fromJson(response.errorBody()?.charStream()?.readText(),BaseResponse::class.java)

                if(unsuccessfulResponse != null) {
                    registerResponse.value = unsuccessfulResponse
                }
            }
        }
    }
}