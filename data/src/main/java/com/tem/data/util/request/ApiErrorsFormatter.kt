package com.tem.data.util.request

import com.google.gson.Gson
import com.tem.data.entity.ApiErrors
import okhttp3.ResponseBody

object ApiErrorsFormatter {
    fun deserialize(responseBody: ResponseBody?): ApiErrors? = Gson().fromJson(responseBody?.string(), ApiErrors::class.java)
}