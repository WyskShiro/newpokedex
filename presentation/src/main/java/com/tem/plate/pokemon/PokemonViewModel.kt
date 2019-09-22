package com.tem.plate.pokemon

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.OnLifecycleEvent
import com.tem.domain.entity.Pokemon
import com.tem.domain.interactor.pokemon.GetPokemon
import com.tem.plate.util.extensions.defaultSched
import com.tem.plate.util.resources.SchedulerProvider
import com.tem.plate.util.structure.base.BaseViewModel
import io.reactivex.rxkotlin.subscribeBy

class PokemonViewModel(
    private val getPokemon: GetPokemon,
    private val schedulerProvider: SchedulerProvider
) : BaseViewModel() {

    val pokemonList: LiveData<List<Pokemon>> get() = _pokemonList

    private val _pokemonList: MutableLiveData<List<Pokemon>> = MutableLiveData()

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    private fun onCreate() {
        getPokemon
            .list()
            .defaultSched(schedulerProvider)
            .subscribeBy(::onError, ::onSuccess)
            .let(disposables::add)
    }

    private fun onError(throwable: Throwable) {

    }

    private fun onSuccess(pokemons: List<Pokemon>) {
        _pokemonList.value = pokemons
    }
}