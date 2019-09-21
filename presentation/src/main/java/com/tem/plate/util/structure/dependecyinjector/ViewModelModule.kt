package com.tem.plate.util.structure.dependecyinjector

import com.tem.plate.fruit.FruitViewModel
import com.tem.plate.util.structure.base.BaseViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { BaseViewModel() }
    viewModel { FruitViewModel() }
}