package com.tem.plate.util.extensions

import android.text.Editable
import android.view.View
import android.widget.TextView
import com.google.android.material.textfield.TextInputLayout
import com.tem.plate.util.resources.SafeClickListener
import com.tem.plate.util.resources.TextWatcher

// TextInputLayout
fun TextInputLayout.getText(): String? {
    return editText?.toString()
}

fun TextInputLayout.observeChanges(callback: (String) -> Unit) {
    editText?.observeChanges(callback)
}

// TextView
fun TextView.observeChanges(callback: (String) -> Unit) {
    addTextChangedListener(object : TextWatcher() {
        override fun afterTextChanged(s: Editable) {
            callback(s.toString())
        }
    })
}

// View
fun View.setOnClickHandler(callback: () -> Unit) {
    val intervalInMillis = 1000
    SafeClickListener(callback, intervalInMillis).apply {
        setOnClickListener(this::onClick)
    }
}

// views

fun View.setVisible(visible: Boolean) {
    visibility = if (visible) View.VISIBLE else View.GONE
}

fun View.isVisible(): Boolean {
    return visibility == View.VISIBLE
}