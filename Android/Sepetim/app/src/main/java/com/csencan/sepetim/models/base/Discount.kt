package com.csencan.sepetim.models.base

import com.google.gson.annotations.SerializedName
import java.util.*
import kotlin.collections.ArrayList

data class Discount(
    @SerializedName("id")
    override val id: String?,

    @SerializedName("created")
    override val created: Date?,

    @SerializedName("lastUpdated")
    override val lastUpdated: Date?,

    @SerializedName("vendor")
    val vendor : Vendor?,

    @SerializedName("beginDate")
    val beginDate : Date?,

    @SerializedName("endDate")
    val endDate : Date?,

    @SerializedName("bannerPageLinks")
    val bannerPageLinks : ArrayList<String>?,

    @SerializedName("products")
    val products : ArrayList<Product>?

) : BaseModel
