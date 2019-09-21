package com.tem.plate.util.extensions

import android.content.Context
import android.view.WindowManager
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.tem.plate.databinding.IncludedToolbarBinding


// toolbar

fun AppCompatActivity.showHomeButton() {
    supportActionBar!!.setDisplayShowHomeEnabled(true)
    supportActionBar!!.setDisplayHomeAsUpEnabled(true)
}

//Toolbar

fun AppCompatActivity.setupToolbar(
    toolbar: Toolbar?,
    showHome: Boolean = true,
    title: String? = null
) {
    if (title != null) {
        setupToolbarWithTitle(toolbar, showHome, title)
    } else {
        setupToolbar(toolbar, showHome)
    }
}

private fun AppCompatActivity.setupToolbarWithTitle(
    toolbar: Toolbar?,
    showHome: Boolean,
    title: String = ""
) {
    setSupportActionBar(toolbar)
    supportActionBar?.run {
        toolbar?.title = title
        setDisplayHomeAsUpEnabled(showHome)
        setDisplayShowHomeEnabled(showHome)
    }
}

//Toolbar
fun AppCompatActivity.setupCustomizedToolbar(
    includedToolbarBinding: IncludedToolbarBinding,
    showHome: Boolean = true,
    title: String? = null
) {
    if (title != null) {
        includedToolbarBinding.toolbarTitle.text = title
    }
    setupToolbar(includedToolbarBinding.toolbar, showHome)
}

//SoftKeyboard

fun AppCompatActivity.hideSoftKeyboard() {
    currentFocus?.let {
        val inputMethodManager =
            getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(it.windowToken, 0)
    }
}

fun AppCompatActivity.showSoftKeyboard() {
    window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE)
}
