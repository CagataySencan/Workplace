package com.csencan.sepetim.utils

import com.csencan.sepetim.models.base.BaseResponse
import com.csencan.sepetim.models.base.User
import com.csencan.sepetim.models.register.RegisterRequestModel

import retrofit2.Call
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface RestApiOperations {
    // /register endpointi response olarak user objesi dönmektedir
    @Headers("Content-Type: application/json")
    @POST("api/v1/auth/register")
    suspend fun addUser(@Body userData: RegisterRequestModel): Response<BaseResponse<*>>

}