package com.csencan.sepetim.models.base

import com.google.gson.annotations.SerializedName

data class BaseFault(
    @SerializedName("code")
    val code : Int,

    @SerializedName("shortDes")
    val shortDes : String,

    @SerializedName("longDes")
    val longDes : String
) {

}