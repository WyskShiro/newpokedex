package com.tem.data.api

import com.tem.data.entity.ApiPokemon
import com.tem.data.entity.ApiResult

/**
 * The bridge between ApiService and DefaultRepositories
 * */
class ApiClient(
    private val apiService: ApiService
) : RequestHandler() {

    /**
     * Pokemon
     * */

    suspend fun getPokemonList(offset: Int?, limit: Int?): ApiResult? {
        return makeRequest {
            apiService.getPokemonList(offset, limit)
        }
    }

    suspend fun getPokemonDetails(id: Int?): ApiPokemon? {
        return makeRequest {
            apiService.getPokemonDetails(id)
        }
    }
}
