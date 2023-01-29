package com.csencan.sepetim.models.base

import com.google.gson.annotations.SerializedName
import java.util.*

interface BaseModel {
    val id : String?
    val created : Date?
    val lastUpdated : Date?

}