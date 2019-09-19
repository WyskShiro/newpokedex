package com.tem.data.api

import com.tem.data.entity.ApiFruit
import io.reactivex.Single
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface ApiService {

    /**
     * ApiFruit
     * */

    @GET("fruit")
    fun getFruit(): Single<Response<ApiFruit>>

    @POST("apiFruit")
    fun postFruit(@Query("apiFruit") apiFruit: ApiFruit): Single<Response<Void>>
}
