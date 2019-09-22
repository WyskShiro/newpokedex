package com.tem.plate.pokemon

import android.content.Context
import android.content.Intent
import com.tem.plate.util.structure.navigation.NavData

class PokemonListNavData : NavData {
    override fun createIntent(context: Context): Intent {
        return PokemonListActivity.createIntent(context)
    }
}