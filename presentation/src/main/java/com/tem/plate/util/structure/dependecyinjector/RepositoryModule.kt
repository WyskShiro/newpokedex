package com.tem.plate.util.structure.dependecyinjector

import com.tem.data.repository.DefaultPokemonRepository
import com.tem.domain.boundary.PokemonRepository
import org.koin.dsl.module

val repositoryModule = module {
    factory<PokemonRepository> { DefaultPokemonRepository(get()) }
}