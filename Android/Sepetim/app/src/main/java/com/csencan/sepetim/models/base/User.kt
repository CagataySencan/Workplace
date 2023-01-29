package com.csencan.sepetim.models.base

import com.google.gson.annotations.SerializedName
import java.util.*
import kotlin.collections.ArrayList

data class User(
    @SerializedName("id")
    override val id: String?,

    @SerializedName("created")
    override val created: Date?,

    @SerializedName("lastUpdated")
    override val lastUpdated: Date?,

    @SerializedName("email")
    val email : String,

    @SerializedName("password")
    val password : String,

    @SerializedName("phone")
    val phone : String?,

    @SerializedName("name")
    val name : Name?,

    @SerializedName("birthDate")
    val birthDate : Date?,

    @SerializedName("interests")
    val interests  : ArrayList<String>?,

    @SerializedName("vendors")
    val vendors : ArrayList<Vendor>?,

    @SerializedName("lastLogin")
    val lastLogin : Date?

    ) : BaseModel