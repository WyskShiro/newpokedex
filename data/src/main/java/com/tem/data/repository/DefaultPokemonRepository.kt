package com.tem.data.repository

import com.tem.data.api.ApiClient
import com.tem.data.entity.ApiResult
import com.tem.domain.boundary.PokemonRepository
import com.tem.domain.entity.Pokemon
import io.reactivex.Single

class DefaultPokemonRepository(
    private val apiClient: ApiClient
) : PokemonRepository {
    override fun getPokemonList(offset: Int?, limit: Int?): Single<List<Pokemon>> {
        return apiClient.getPokemonList(offset, limit).map {
            ApiResult.ApiResultToPokemonListMapper.transform(it)
        }
    }
}