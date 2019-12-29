package com.tem.plate.util.dependecyinjector

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.tem.data.api.ApiClient
import com.tem.data.api.ApiService
import com.tem.plate.BuildConfig
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val apiModule = module {
    single<Gson> {
        GsonBuilder()
            .serializeNulls()
            .create()
    }
    single<GsonConverterFactory> {
        GsonConverterFactory.create(get())
    }
    single<Retrofit> {
        Retrofit.Builder()
            .baseUrl(BuildConfig.API_URL)
            .addConverterFactory(get<GsonConverterFactory>())
            .build()
    }
    single<ApiService> {
        get<Retrofit>()
            .create(ApiService::class.java)
    }
    single { ApiClient(get()) }
}