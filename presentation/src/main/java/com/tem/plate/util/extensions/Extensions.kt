package com.tem.plate.util.extensions

import android.content.Intent

fun Intent.shouldClearTask(clearTask: Boolean) {
    if (clearTask) {
        addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
        addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
    }
}