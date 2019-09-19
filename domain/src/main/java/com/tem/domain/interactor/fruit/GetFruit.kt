package com.tem.domain.interactor.fruit

import com.tem.domain.boundary.FruitRepository
import com.tem.domain.entity.Fruit
import io.reactivex.Single

class GetFruit constructor(
    private val repository: FruitRepository
) {

    fun execute(): Single<Fruit> {
        return repository.getFruit()
    }
}
