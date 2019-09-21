package com.tem.plate.fruit

import android.content.Context
import android.content.Intent
import com.tem.plate.util.structure.navigation.NavData

class FruitNavData : NavData {
    override fun createIntent(context: Context): Intent {
        return FruitActivity.createIntent(context)
    }
}