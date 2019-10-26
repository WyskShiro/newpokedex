package com.tem.plate

import com.tem.domain.boundary.PokemonRepository
import com.tem.plate.MocksEntities.mockedFirst5Pokemons
import io.reactivex.Single
import org.mockito.Mockito

object MocksRepository {

    fun returnMockedPokemonRepository(): PokemonRepository {
        val mockRepository = Mockito.mock(PokemonRepository::class.java)
        Mockito.`when`(mockRepository.getPokemonList(0, 10))
            .thenReturn(Single.just(mockedFirst5Pokemons))
        return mockRepository
    }
}
