package com.tem.domain.entity

import java.io.Serializable

data class Pokemon(
    var id: Int,
    var name: String,
    var url: String? = null,
    var sprites: Sprites? = null,
    var types: List<PokemonType>? = null
) : Serializable {

    fun getSafeType(position: Int) = types?.getOrNull(position)?.name ?: ""
}
