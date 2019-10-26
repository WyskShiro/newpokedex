package com.tem.plate.pokemon

import com.tem.domain.entity.Pokemon
import com.tem.domain.entity.PokemonType
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class PokemonTest {

    @Test
    fun pokemonWithOneType() {
        pokemonWithOneType
        assert(pokemonWithOneType.getSafeType(0) != "")
        assert(pokemonWithOneType.getSafeType(1) == "")
    }

    @Test
    fun pokemonWithTwoTypes() {
        assert(pokemonWithTwoTypes.getSafeType(0) != "")
        assert(pokemonWithTwoTypes.getSafeType(1) != "")
    }

    private val pokemonWithOneType = Pokemon(
        types = listOf(
            PokemonType(name = PokemonType.Type.FIRE)
        )
    )

    private val pokemonWithTwoTypes = Pokemon(
        types = listOf(
            PokemonType(name = PokemonType.Type.FIRE),
            PokemonType(name = PokemonType.Type.GRASS)
        )
    )

}