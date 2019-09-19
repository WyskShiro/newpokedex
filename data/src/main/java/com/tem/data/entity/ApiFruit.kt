package com.tem.data.entity

import com.google.gson.annotations.SerializedName
import com.tem.data.mapper.Mapper
import com.tem.domain.entity.Fruit
import java.io.Serializable

data class ApiFruit(
    @SerializedName("name") val name: String?
) : Serializable {

    object ApiFruitToFruit : Mapper<ApiFruit, Fruit>() {
        override fun transform(t: ApiFruit) = Fruit(
            name = t.name
        )
    }
}