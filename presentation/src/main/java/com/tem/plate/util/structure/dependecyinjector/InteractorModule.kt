package com.tem.plate.util.structure.dependecyinjector

import com.tem.domain.interactor.fruit.GetFruit
import org.koin.dsl.module

val interactorModule = module {
    single { GetFruit(get()) }
}