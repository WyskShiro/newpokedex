package com.tem.data.api

import com.tem.data.entity.ApiResult
import io.reactivex.Single
import retrofit2.Response
import retrofit2.http.GET
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
        @Query("limit") limit: Int?,
        @Query("offset") offset: Int?
    ): Single<Response<ApiResult>>
}
