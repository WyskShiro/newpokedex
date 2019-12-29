package com.tem.plate.util.resources

import android.content.Context
import androidx.annotation.StringRes
import com.tem.domain.util.StringsProvider
import com.tem.plate.R

// The class that actually access context and the strings
class AndroidStringProvider(context: Context) : StringsProvider {
    private val context = context.applicationContext

    override val title: String get() = res(R.string.title)
    override val confirm: String get() = res(R.string.dialog_confirm)
    override val cancel: String get() = res(R.string.dialog_cancel)

    override val errorUnknown: String get() = "Unknown Error"
    override val errorNetwork: String get() = "Network Error"
    override val errorNotFound: String get() = "Not found Error"
    override val errorSocketTimeout: String get() = "Timeout Error"

    override val globalTryAgain: String get() = "Try again"

    private fun res(@StringRes stringId: Int) = context.getString(stringId)
}
