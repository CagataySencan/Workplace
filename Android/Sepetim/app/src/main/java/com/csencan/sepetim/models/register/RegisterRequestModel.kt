package com.csencan.sepetim.models.register

import com.google.gson.annotations.SerializedName

data class RegisterRequestModel(
    @SerializedName("email")
    val email : String,

    @SerializedName("password")
    val password : String
)
