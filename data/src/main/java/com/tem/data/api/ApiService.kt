package com.tem.data.api

import com.tem.data.entity.ApiPokemon
import com.tem.data.entity.ApiResult
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

/**
 * Declare urls for API
 */
interface ApiService {

    /**
     * Pokemon
     * */

    @GET("pokemon")
    suspend fun getPokemonList(
        @Query("offset") offset: Int?,
        @Query("limit") limit: Int?
    ): Response<ApiResult>

    @GET("pokemon/{id}")
    suspend fun getPokemonDetails(@Path("id") id: Int?): Response<ApiPokemon>
}
