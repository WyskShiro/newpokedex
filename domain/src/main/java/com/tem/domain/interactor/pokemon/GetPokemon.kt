package com.tem.domain.interactor.pokemon

import com.tem.domain.boundary.PokemonRepository
import com.tem.domain.entity.Pokemon
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class GetPokemon constructor(
    private val repository: PokemonRepository
) {

    suspend fun list(offset: Int? = 0, limit: Int? = 20): List<Pokemon>? {
        return withContext(Dispatchers.IO) {
            repository.getPokemonList(offset, limit)
        }
    }

    suspend fun details(id: Int): Pokemon? {
        return withContext(Dispatchers.IO) {
            repository.getPokemonDetails(id)
        }
    }
}
