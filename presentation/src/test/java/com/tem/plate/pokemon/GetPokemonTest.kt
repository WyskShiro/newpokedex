package com.tem.plate.pokemon

import com.tem.domain.boundary.PokemonRepository
import com.tem.domain.entity.Pokemon
import com.tem.domain.entity.PokemonType
import com.tem.domain.entity.Sprites
import com.tem.domain.interactor.pokemon.GetPokemon
import io.reactivex.Single
import org.junit.jupiter.api.*
import org.mockito.Mockito.`when`
import org.mockito.Mockito.mock

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

    private val mockedFirst5Pokemons = listOf(
        Pokemon(
            id = 1,
            name = "Bulbassaur",
            url = "Bulbassaur.com",
            sprites = Sprites(),
            types = listOf(
                PokemonType(name = PokemonType.Type.POISON),
                PokemonType(name = PokemonType.Type.GRASS)
            )
        ),
        Pokemon(
            id = 2,
            name = "Ivyssaur",
            url = "Ivyssaur.com",
            sprites = Sprites(),
            types = listOf(
                PokemonType(name = PokemonType.Type.POISON),
                PokemonType(name = PokemonType.Type.GRASS)
            )
        ),
        Pokemon(
            id = 3,
            name = "Venussaur",
            url = "Venussaur.com",
            sprites = Sprites(),
            types = listOf(
                PokemonType(name = PokemonType.Type.POISON),
                PokemonType(name = PokemonType.Type.GRASS)
            )
        ),
        Pokemon(
            id = 4,
            name = "Charmander",
            url = "Charmander.com",
            sprites = Sprites(),
            types = listOf(
                PokemonType(name = PokemonType.Type.FIRE)
            )
        ),
        Pokemon(
            id = 5,
            name = "Charmeleon",
            url = "Charmeleon.com",
            sprites = Sprites(),
            types = listOf(
                PokemonType(name = PokemonType.Type.FIRE)
            )
        )
    )
}
