package com.csencan.sepetim.models.base

import com.google.gson.annotations.SerializedName

data class BaseResponse<T>(
    @SerializedName("data")
    val data : T?,

    @SerializedName("fault")
    val fault : BaseFault?,

    @SerializedName("status")
    val status : Int,

    )