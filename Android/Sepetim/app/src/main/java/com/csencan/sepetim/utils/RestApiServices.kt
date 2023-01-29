package com.csencan.sepetim.utils

import com.csencan.sepetim.models.base.BaseResponse
import com.csencan.sepetim.models.base.User
import com.csencan.sepetim.models.register.RegisterRequestModel
import com.google.gson.Gson
import org.json.JSONObject

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

object RestApiServices {
    private val retrofitClient = ServiceClient.buildService(RestApiOperations::class.java)

    suspend fun addUser(userData: RegisterRequestModel) : Response<BaseResponse<*>> {
        return retrofitClient.addUser(userData)

    }

}