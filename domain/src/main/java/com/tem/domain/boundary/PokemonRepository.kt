package com.tem.domain.boundary

import com.tem.domain.entity.Pokemon

interface PokemonRepository {
    suspend fun getPokemonList(offset: Int?, limit: Int?): List<Pokemon>?
    suspend fun getPokemonDetails(id: Int?): Pokemon?
}