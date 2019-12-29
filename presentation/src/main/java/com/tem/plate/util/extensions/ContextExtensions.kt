package com.tem.plate.util.extensions

import android.app.AlertDialog
import android.app.Dialog
import android.content.Context
import android.content.DialogInterface
import android.widget.Toast
import androidx.annotation.ColorRes
import androidx.annotation.DrawableRes
import androidx.core.content.ContextCompat
import com.tem.plate.R
import com.tem.plate.util.viewmodels.DialogData

fun Context.shortToast(message: String) =
    Toast.makeText(this, message, Toast.LENGTH_SHORT).show()

fun Context.longToast(message: String) =
    Toast.makeText(this, message, Toast.LENGTH_LONG).show()

fun Context.showDialog(dialogData: DialogData): Dialog {
    val builder = AlertDialog.Builder(this)
    builder.setTitle(dialogData.title)
    builder.setMessage(dialogData.message)
    if (dialogData.confirmButtonText == null && dialogData.onConfirm == null) {
        builder.setPositiveButton(dialogData.dismissButtonText, dialogData.onDismiss)
    } else {
        builder.setPositiveButton(dialogData.confirmButtonText, dialogData.onConfirm
            ?: dialogData.onDismiss)
        if (dialogData.dismissButtonText != null || dialogData.onDismiss != null) {
            builder.setNegativeButton(dialogData.dismissButtonText, dialogData.onDismiss)
        }
    }
    dialogData.onDismiss?.let { builder.setOnCancelListener { it() } }
    builder.setCancelable(dialogData.cancelable ?: true)
    return builder.show()
}

fun AlertDialog.Builder.setPositiveButton(buttonText: String?, onClick: (() -> Unit)?): AlertDialog.Builder = setPositiveButton(
    buttonText ?: context.getString(R.string.global_ok),
    onClick?.let { { _: DialogInterface, _: Int -> it() } }
)

fun AlertDialog.Builder.setNegativeButton(buttonText: String?, onClick: (() -> Unit)?): AlertDialog.Builder = setNegativeButton(
    buttonText ?: context.getString(R.string.global_cancel),
    onClick?.let { { _: DialogInterface, _: Int -> it() } }
)

// resources

fun Context.colorCompat(@ColorRes colorId: Int) = ContextCompat.getColor(this, colorId)

fun Context.drawableCompat(@DrawableRes drawableId: Int) =
    ContextCompat.getDrawable(this, drawableId)