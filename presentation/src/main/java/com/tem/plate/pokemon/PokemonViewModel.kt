package com.tem.plate.pokemon

import android.util.Log
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.OnLifecycleEvent
import com.tem.domain.entity.Pokemon
import com.tem.domain.interactor.pokemon.GetPokemon
import com.tem.plate.util.structure.base.BaseViewModel

class PokemonViewModel(
    private val getPokemon: GetPokemon
) : BaseViewModel() {

    val pokemonList: LiveData<List<Pokemon>> get() = _pokemonList
    val pokemonDetails: LiveData<Pokemon> get() = _pokemonDetails

    private val _pokemonList: MutableLiveData<List<Pokemon>> = MutableLiveData()
    private val _pokemonDetails: MutableLiveData<Pokemon> = MutableLiveData()

    fun onRecyclerItemClicked(pokemon: Pokemon) {
        launchDataLoad(true, ::onFailure) {
            _pokemonDetails.value = getPokemon.details(pokemon.id)
        }
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    private fun onCreate() {
        launchDataLoad(true, ::onFailure) {
            _pokemonList.value = getPokemon.list()
        }
    }
}