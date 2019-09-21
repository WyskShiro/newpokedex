package com.tem.domain.interactor.pokemon

import com.tem.domain.boundary.PokemonRepository
import com.tem.domain.entity.Pokemon
import io.reactivex.Single

class GetPokemon constructor(
    private val repository: PokemonRepository
) {

    fun list(offset: Int? = 0, limit: Int? = 20): Single<List<Pokemon>> {
        return repository.getPokemonList(offset, limit)
    }
}
