package com.tem.data.entity

import com.google.gson.annotations.SerializedName
import com.tem.data.mapper.Mapper
import com.tem.domain.entity.Fruit
import java.io.Serializable

/**
 * How entities are mapped in the API (the majority of them uses snake_case)
 * It is also used for "map" the entities to one with an attribute or type different for the Android application
 *
 * */

data class ApiFruit(
    @SerializedName("name") val name: String?
) : Serializable {

    object ApiFruitToFruit : Mapper<ApiFruit, Fruit>() {
        override fun transform(t: ApiFruit) = Fruit(
            name = t.name
        )
    }
}