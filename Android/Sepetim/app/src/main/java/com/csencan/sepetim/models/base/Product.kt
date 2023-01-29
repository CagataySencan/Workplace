package com.csencan.sepetim.models.base

import com.google.gson.annotations.SerializedName
import java.util.*

data class Product(
    @SerializedName("id")
    override val id: String?,

    @SerializedName("created")
    override val created: Date?,

    @SerializedName("lastUpdated")
    override val lastUpdated: Date?,

    @SerializedName("vendor")
    val vendor : Vendor?,

    @SerializedName("brand")
    val brand : String?,

    @SerializedName("title")
    val title : String?,

    @SerializedName("description")
    val description : String?,

    @SerializedName("url")
    val url : String?,

    @SerializedName("imageUrl")
    val imageUrl : String?,

    @SerializedName("oldPrice")
    val oldPrice : Double?,

    @SerializedName("price")
    val price : Double?,

    @SerializedName("discountEndDate")
    val discountEndDate : Date?,

    @SerializedName("inDiscount")
    val inDiscount : Boolean?,

    @SerializedName("inActual")
    val inActual : Boolean?,

    @SerializedName("isImageOnly")
    val isImageOnly : Boolean?
) : BaseModel
