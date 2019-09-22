package com.tem.data.entity

import com.google.gson.annotations.SerializedName
import com.tem.data.mapper.Mapper
import com.tem.domain.entity.Sprites
import java.io.Serializable

data class ApiSprites(
    @SerializedName("back_default") val backUrl: String?,
    @SerializedName("back_shiny") val backShinyUrl: String?,
    @SerializedName("front_default") val frontUrl: String?,
    @SerializedName("front_shiny") val frontShinyUrl: String?
) : Serializable {

    object ApiSpriteToSprite : Mapper<ApiSprites, Sprites>() {
        override fun transform(t: ApiSprites) = Sprites(
            backUrl = t.backUrl,
            backUrlShiny = t.backShinyUrl,
            frontUrl = t.frontUrl,
            frontUrlShiny = t.frontShinyUrl
        )
    }
}