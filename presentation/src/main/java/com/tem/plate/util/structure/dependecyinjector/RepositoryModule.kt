package com.tem.plate.util.structure.dependecyinjector

import com.tem.data.repository.DefaultFruitRepository
import com.tem.domain.boundary.FruitRepository
import org.koin.dsl.module

val repositoryModule = module {
    factory<FruitRepository> { DefaultFruitRepository(get()) }
}