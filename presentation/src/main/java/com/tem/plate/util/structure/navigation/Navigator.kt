package com.tem.plate.util.structure.navigation

import android.content.Context
import com.tem.plate.MainActivity

object Navigator {

    fun goTo(context: Context?, navData: NavData) {
        context?.let {
            val intent = navData.createIntent(it)
            it.startActivity(intent)
        }
    }

    fun goToMain(context: Context, clearTask: Boolean = false) {
        context.startActivity(MainActivity.createIntent(context).apply { shouldClearTask(clearTask) })
    }
}