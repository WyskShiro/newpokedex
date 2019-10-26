package com.tem.data.entity

import com.google.gson.annotations.SerializedName
import com.tem.data.mapper.Mapper
import com.tem.domain.entity.PokemonType
import java.io.Serializable

data class ApiPokemonSlotType(
    @SerializedName("slot") val slot: Int?,
    @SerializedName("type") val type: ApiPokemonType?
) : Serializable {

    object ApiPokemonSlotTypeToPokemonType : Mapper<ApiPokemonSlotType, PokemonType>() {
        override fun transform(t: ApiPokemonSlotType): PokemonType {
            return PokemonType(
                slot = t.slot,
                name = t.type?.name,
                url = t.type?.url
            )
        }
    }
}