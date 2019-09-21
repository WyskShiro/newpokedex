package com.tem.plate.util.structure.dependecyinjector

import com.tem.domain.interactor.pokemon.GetPokemon
import org.koin.dsl.module

val interactorModule = module {
    single { GetPokemon(get()) }
}