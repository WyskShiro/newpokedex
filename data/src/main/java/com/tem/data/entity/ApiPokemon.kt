package com.tem.data.entity

import com.google.gson.annotations.SerializedName
import com.tem.data.mapper.Mapper
import com.tem.domain.entity.Pokemon
import com.tem.domain.util.ApiFieldsException
import java.io.Serializable

/**
 * How entities are mapped in the API (the majority of them uses snake_case)
 * It is also used for "map" the entities to one with an attribute or type different for the Android application
 *
 * */

data class ApiPokemon(
    @SerializedName("id") val id: Int?,
    @SerializedName("name") val name: String?,
    @SerializedName("url") val url: String?,
    @SerializedName("sprites") val sprites: ApiSprites?,
    @SerializedName("types") val types: List<ApiPokemonSlotType>
) : Serializable {

    private fun getPokemonId(): Int? {
        return try {
            url?.replace(URL_PREFIX, "")?.replace("/", "")?.toInt()
        } catch (e: NumberFormatException) {
            null
        }
    }

    object ApiPokemonToPokemonMapper : Mapper<ApiPokemon, Pokemon>() {
        override fun transform(t: ApiPokemon) = Pokemon(
            id = t.getPokemonId() ?: throw ApiFieldsException(
                this,
                "An ApiPokemon should always have an ID"
            ),
            name = t.name ?: throw ApiFieldsException(
                this,
                "An ApiPokemon should always have an name"
            ),
            url = t.url,
            sprites = t.sprites?.run(ApiSprites.ApiSpriteToSprite::transform),
            types = ApiPokemonSlotType.ApiPokemonSlotTypeToPokemonType.transform(t.types)
        )
    }

    companion object {
        private const val URL_PREFIX = "https://pokeapi.co/api/v2/pokemon/"
    }
}