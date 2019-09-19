package com.ufms.mediadorpedagogico.presentation.signup

import android.content.Context
import android.content.Intent
import com.ufms.mediadorpedagogico.presentation.util.structure.navigation.NavData

class FruitNavData : NavData {
    override fun createIntent(context: Context): Intent {
        return FruitActivity.createIntent(context)
    }
}