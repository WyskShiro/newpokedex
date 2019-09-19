package com.tem.domain.boundary

import com.tem.domain.entity.Fruit
import io.reactivex.Single

interface FruitRepository {
    fun getFruit(): Single<Fruit>
}