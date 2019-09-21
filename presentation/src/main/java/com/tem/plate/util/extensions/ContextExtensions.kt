package com.tem.plate.util.extensions

import android.content.Context
import android.widget.Toast
import androidx.annotation.ColorRes
import androidx.annotation.DrawableRes
import androidx.core.content.ContextCompat

fun Context.shortToast(message: String) =
    Toast.makeText(this, message, Toast.LENGTH_SHORT).show()

fun Context.longToast(message: String) =
    Toast.makeText(this, message, Toast.LENGTH_LONG).show()

// resources

fun Context.colorCompat(@ColorRes colorId: Int) = ContextCompat.getColor(this, colorId)

fun Context.drawableCompat(@DrawableRes drawableId: Int) =
    ContextCompat.getDrawable(this, drawableId)