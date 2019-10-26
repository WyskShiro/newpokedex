package com.tem.plate

import com.tem.domain.entity.Pokemon
import com.tem.domain.entity.PokemonType
import com.tem.domain.entity.Sprites

object MocksEntities {

    val mockedFirst5Pokemons = listOf(
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