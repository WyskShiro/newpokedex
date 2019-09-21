package com.tem.data.entity

import com.google.gson.annotations.SerializedName
import com.tem.data.mapper.Mapper
import com.tem.domain.entity.Pokemon
import java.io.Serializable

/**
 * How entities are mapped in the API (the majority of them uses snake_case)
 * It is also used for "map" the entities to one with an attribute or type different for the Android application
 *
 * */

data class ApiPokemon(
    @SerializedName("id") val id: Int?,
    @SerializedName("name") val name: String?,
    @SerializedName("url") val url: String?
) : Serializable {

    object ApiPokemonToPokemonMapper : Mapper<ApiPokemon, Pokemon>() {
        override fun transform(t: ApiPokemon) = Pokemon(
            id = t.id,
            name = t.name,
            url = t.url
        )
    }
}