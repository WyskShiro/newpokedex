package com.tem.plate

import com.tem.domain.boundary.PokemonRepository
import com.tem.domain.interactor.pokemon.GetPokemon
import com.tem.plate.MocksEntities.mockedFirst5Pokemons
import com.tem.plate.MocksRepository.returnMockedPokemonRepository
import io.reactivex.Single
import org.junit.jupiter.api.*
import org.mockito.Mockito.`when`
import org.mockito.Mockito.mock
import java.lang.Exception

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class GetPokemonTest {

    private lateinit var mockedRepository: PokemonRepository

    @BeforeAll
    fun init() {
        mockedRepository = mock(PokemonRepository::class.java)
    }

    @Test
    fun executeSuccess() {
        val _OFFSET = 0
        val _LIMIT = 10

        `when`(mockedRepository.getPokemonList(_OFFSET, _LIMIT)).thenReturn(Single.just(mockedFirst5Pokemons))

        val pokemon = GetPokemon(mockedRepository).list(_OFFSET, _LIMIT).blockingGet()
        assert(pokemon == mockedFirst5Pokemons)
    }
}
