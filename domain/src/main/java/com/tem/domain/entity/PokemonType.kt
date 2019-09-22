package com.tem.domain.entity

import java.io.Serializable

data class PokemonType(
    var slot: Int? = null,
    var name: String? = null,
    var url: String? = null
) : Serializable {

    object Type {
        const val FIRE = "fire"
        const val ICE = "ice"
        const val GRASS = "grass"
        const val DARK = "dark"
        const val DRAGON = "dragon"
        const val ELETRIC = "eletric"
        const val FAIRY = "fairy"
        const val FIGHTING = "fighting"
        const val FLYING = "flying"
        const val GHOST = "ghost"
        const val GROUND = "ground"
        const val NORMAL = "normal"
        const val POISON = "poison"
        const val PSYCHIC = "psychic"
        const val ROCK = "rock"
        const val STEEL = "steel"
        const val WATER = "water"
    }
}