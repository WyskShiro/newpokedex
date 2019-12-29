package com.tem.data.repository

import com.tem.data.api.ApiClient
import com.tem.data.entity.ApiPokemon
import com.tem.data.entity.ApiResult
import com.tem.domain.boundary.PokemonRepository
import com.tem.domain.entity.Pokemon

class DefaultPokemonRepository(
    private val apiClient: ApiClient
) : PokemonRepository {

    override suspend fun getPokemonList(offset: Int?, limit: Int?): List<Pokemon>? {
        return apiClient.getPokemonList(offset, limit)?.let {
            ApiResult.ApiResultToPokemonListMapper.transform(it)
        }
    }

    override suspend fun getPokemonDetails(id: Int?): Pokemon? {
        return apiClient.getPokemonDetails(id)?.let {
            ApiPokemon.ApiPokemonToPokemonMapper.transform(it)
        }
    }
}