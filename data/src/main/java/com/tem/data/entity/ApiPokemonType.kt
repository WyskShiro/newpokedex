package com.tem.data.entity

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class ApiPokemonType(
    @SerializedName("name") val name: String?,
    @SerializedName("url") val url: String?
) : Serializable