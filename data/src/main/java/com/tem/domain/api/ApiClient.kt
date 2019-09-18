package com.tem.domain.api

import com.tem.domain.BuildConfig

object ApiClient {

    private const val apiEndpoint = BuildConfig.API_ENDPOINT
    private lateinit var retrofit: Retrofit
    private lateinit var authInterceptor: AuthInterceptor
    private var apiServiceSingleton: ApiService? = null

    private val apiServices: ApiService get() = apiServiceSingleton ?: buildApiServices()

    fun signIn(classKey: String, name: String): Single<ApiUser> {
        return makeRequest(apiServices.signIn(classKey, name))
    }

    fun getListOfHomework(pageNumber: Int): Single<ApiHomeworkContent> {
        return makeRequest(apiServices.getListOfHomework(pageNumber))
    }

    fun getListOfNotice(pageNumber: Int): Single<ApiNoticeContent> {
        return makeRequest(apiServices.getListOfNotices(pageNumber))
    }

    /**
     *
     * - ApiService, Retrofit, AuthInterceptor builders
     * - Response and Request Handler Methods
     *
     **/

    private fun buildApiServices(): ApiService {
        val okHttpClientBuilder = okHttpClientBuilder()
        retrofit = Retrofit.Builder()
            .client(okHttpClientBuilder.build())
            .baseUrl(apiEndpoint)
            .addConverterFactory(
                GsonConverterFactory.create(
                    GsonBuilder()
                        .serializeNulls()
                        .setDateFormat(DateFormat.FULL)
                        .create()
                )
            )
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()

        with(retrofit.create(ApiService::class.java)) {
            apiServiceSingleton = this
            return this
        }
    }

    private fun okHttpClientBuilder(): OkHttpClient.Builder {
        authInterceptor = AuthInterceptor()
        val okHttpClientBuilder = OkHttpClient.Builder()
        okHttpClientBuilder.addInterceptor(HttpLoggingInterceptor().setLevel(resolveLevelInterceptor()))
        okHttpClientBuilder.addInterceptor(authInterceptor)
        return okHttpClientBuilder
    }

    private fun resolveLevelInterceptor() =
        if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY else HttpLoggingInterceptor.Level.NONE

    private fun <T> verifyResponseException(): SingleTransformer<Response<T>, Response<T>> {
        return SingleTransformer { upstream ->
            upstream.doOnSuccess { response ->
                if (!response.isSuccessful) {
                    throw RequestException.httpError(response.code(), response.errorBody())
                }
            }
        }
    }

    private fun <T> verifyRequestException(): SingleTransformer<Response<T>, Response<T>> {
        return SingleTransformer { upstream ->
            upstream.onErrorResumeNext { t ->
                when (t) {
                    is RequestException -> Single.error(t)
                    is SocketTimeoutException -> Single.error(RequestException.timeoutError(t))
                    is IOException -> Single.error(RequestException.networkError(t))
                    else -> Single.error(RequestException.unexpectedError(t))
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
            .compose(verifyRequestException())
            .compose(unwrap())
    }

    private fun <T> justVerifyErrors(request: Single<Response<T>>): Completable {
        return request.compose(verifyResponseException())
            .compose(verifyRequestException())
            .ignoreElement()
    }

    private fun buildSignUpMultipartBody(fields: Map<String, String?>): MultipartBody {
        val builder = MultipartBody.Builder().setType(MultipartBody.FORM)
        for ((key, value) in fields) {
            if ("avatar" == key) {
                if (value == null) continue
                val file = File(value)
                builder.addFormDataPart(
                    key,
                    file.name,
                    RequestBody.create(MediaType.parse("image/*"), file)
                )
            } else {
                value?.let { builder.addFormDataPart(key, value) }
            }
        }
        return builder.build()
    }
}
