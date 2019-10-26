package com.tem.data.api

import com.tem.data.entity.ApiPokemon
import com.tem.data.entity.ApiResult
import io.reactivex.Single
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
    fun getPokemonList(
        @Query("offset") offset: Int?,
        @Query("limit") limit: Int?
    ): Single<Response<ApiResult>>

    @GET("pokemon/{id}")
    fun getPokemonDetails(@Path("id") id: Int?): Single<Response<ApiPokemon>>
}
