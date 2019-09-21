package com.tem.plate.util.resources

import android.content.Context
import androidx.annotation.StringRes
import com.tem.domain.util.StringsProvider
import com.tem.plate.R

// The class that actually access context and the strings
class AndroidStringProvider(context: Context) : StringsProvider {
    private val context = context.applicationContext

    override val fruitTitle: String get() = res(R.string.fruit_title)

    private fun res(@StringRes stringId: Int) = context.getString(stringId)
}
