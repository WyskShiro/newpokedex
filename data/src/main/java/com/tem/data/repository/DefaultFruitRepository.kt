package com.tem.data.repository

import com.tem.data.api.ApiClient
import com.tem.domain.boundary.FruitRepository

class DefaultFruitRepository(
    private val apiClient: ApiClient
) : FruitRepository {

    override fun getFruit() = apiClient.getFruit()
}