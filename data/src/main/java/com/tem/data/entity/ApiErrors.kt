package com.tem.data.entity

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class ApiErrors(
    @SerializedName("message")
    val errorMessage: String?,
    @SerializedName("errors")
    val errors: ArrayList<String>?
) : Serializable