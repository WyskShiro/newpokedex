package com.tem.plate.util.dependecyinjector

import com.tem.domain.interactor.pokemon.GetPokemon
import org.koin.dsl.module

val interactorModule = module {
    single { GetPokemon(get()) }
}