package com.tem.data.entity

import com.google.gson.annotations.SerializedName
import com.tem.data.mapper.Mapper
import com.tem.domain.entity.Pokemon
import java.io.Serializable

data class ApiResult(
    @SerializedName("count") val count: Int?,
    @SerializedName("results") val results: List<ApiPokemon>?
): Serializable {

    object ApiResultToPokemonListMapper: Mapper<ApiResult, List<Pokemon>>() {
        override fun transform(t: ApiResult): List<Pokemon> {
            return ApiPokemon.ApiPokemonToPokemonMapper.transform(t.results) ?: emptyList()
        }
    }
}