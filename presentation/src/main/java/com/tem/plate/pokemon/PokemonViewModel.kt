package com.tem.plate.pokemon

import android.util.Log
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
    val pokemonDetails: LiveData<Pokemon> get() = _pokemonDetails

    private val _pokemonList: MutableLiveData<List<Pokemon>> = MutableLiveData()
    private val _pokemonDetails: MutableLiveData<Pokemon> = MutableLiveData()

    fun onRecyclerItemClicked(pokemon: Pokemon) {
        pokemon.id?.let {
            getPokemon
                .details(it)
                .defaultSched(schedulerProvider)
                .subscribeBy(::onError, ::onGetDetailsSuccess)
                .let(disposables::add)
        }
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    private fun onCreate() {
        getPokemon
            .list()
            .defaultSched(schedulerProvider)
            .subscribeBy(::onError, ::onGetListSuccess)
            .let(disposables::add)
    }

    private fun onGetListSuccess(pokemons: List<Pokemon>) {
        _pokemonList.value = pokemons
    }

    private fun onGetDetailsSuccess(pokemon: Pokemon) {
        _pokemonDetails.value = pokemon
    }

    private fun onError(throwable: Throwable) {
        Log.d("ERROR", throwable.message)
    }
}