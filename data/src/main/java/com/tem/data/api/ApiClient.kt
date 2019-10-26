package com.tem.data.api

import com.tem.data.entity.ApiPokemon
import com.tem.data.entity.ApiResult
import io.reactivex.Single
import io.reactivex.SingleTransformer
import retrofit2.Response

/**
 * The bridge between ApiService and DefaultRepositories
 * */
class ApiClient(
    private val apiService: ApiService
) {

    /**
     * Pokemon
     * */

    fun getPokemonList(offset: Int?, limit: Int?): Single<ApiResult> {
        return makeRequest(apiService.getPokemonList(offset, limit))
    }

    fun getPokemonDetails(id: Int?): Single<ApiPokemon> {
        return makeRequest(apiService.getPokemonDetails(id))
    }

    /**
     *
     * - ApiService, Retrofit,
     * - Response and Request Handler Methods
     *
     **/

    private fun <T> verifyResponseException(): SingleTransformer<Response<T>, Response<T>> {
        return SingleTransformer { upstream ->
            upstream.doOnSuccess { response ->
                if (!response.isSuccessful) {
                    throw Throwable("Something went wrong while getting the response")
                }
            }
        }
    }

    private fun <T> unwrap(): SingleTransformer<Response<T>, T> {
        return SingleTransformer { upstream ->
            upstream.map<T> { it.body()!! }
        }
    }

    private fun <T> makeRequest(request: Single<Response<T>>): Single<T> {
        return request.compose(verifyResponseException())
            .compose(unwrap())
    }
}
