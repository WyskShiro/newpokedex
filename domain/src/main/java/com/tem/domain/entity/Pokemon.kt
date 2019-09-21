package com.tem.domain.entity

import java.io.Serializable

data class Pokemon(
    var id: Int? = null,
    var name: String? = null,
    var url: String? = null
) : Serializable
