package com.csencan.sepetim.models.base

import com.google.gson.annotations.SerializedName
import com.tdonuk.constant.VendorType
import java.util.*
import kotlin.collections.ArrayList

data class Vendor(
    @SerializedName("id")
    override val id: String?,

    @SerializedName("created")
    override val created: Date?,

    @SerializedName("lastUpdated")
    override val lastUpdated: Date?,

    @SerializedName("name")
    val name : String?,

    @SerializedName("url")
    val url : String?,

    @SerializedName("type")
    val type : VendorType?,

    @SerializedName("discountHist")
    val discountHist : ArrayList<Discount>?



) : BaseModel