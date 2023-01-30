package com.csencan.sepetim.models.register

import com.csencan.sepetim.models.base.BaseFault
import com.csencan.sepetim.models.base.User
import com.google.gson.annotations.SerializedName

data class LoginResponseModel(
    @SerializedName("message")
    var message : String? = null,

    @SerializedName("fault")
    var fault : BaseFault? = null,

    @SerializedName("status")
    var status : Int? = null
)