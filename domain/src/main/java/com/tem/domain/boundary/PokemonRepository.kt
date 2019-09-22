package com.tem.domain.boundary

import com.tem.domain.entity.Pokemon
import io.reactivex.Single

interface PokemonRepository {
    fun getPokemonList(offset: Int?, limit: Int?): Single<List<Pokemon>>
    fun getPokemonDetails(id: Int?): Single<Pokemon>
}