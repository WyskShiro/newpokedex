package com.tem.data.repository

import com.tem.data.api.ApiClient
import com.tem.data.entity.ApiFruit
import com.tem.domain.boundary.FruitRepository
import com.tem.domain.entity.Fruit
import io.reactivex.Single

class DefaultFruitRepository(
    private val apiClient: ApiClient
) : FruitRepository {

    override fun getFruit(): Single<Fruit> =
        apiClient.getFruit().map(ApiFruit.ApiFruitToFruit::transform)
}