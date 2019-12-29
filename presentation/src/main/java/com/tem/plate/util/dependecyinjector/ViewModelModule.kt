package com.tem.plate.util.dependecyinjector

import com.tem.plate.pokemon.PokemonViewModel
import com.tem.plate.util.structure.base.BaseViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { BaseViewModel() }
    viewModel { PokemonViewModel(get()) }
}