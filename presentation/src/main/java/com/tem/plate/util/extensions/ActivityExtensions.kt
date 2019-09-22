package com.tem.plate.util.extensions

import android.content.Context
import android.view.WindowManager
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.AppCompatActivity
import com.tem.plate.databinding.IncludedToolbarBinding


// toolbar

fun AppCompatActivity.showHomeButton() {
    supportActionBar!!.setDisplayShowHomeEnabled(true)
    supportActionBar!!.setDisplayHomeAsUpEnabled(true)
}

//Toolbar
fun AppCompatActivity.setupToolbar(
    includedToolbarBinding: IncludedToolbarBinding,
    showHome: Boolean = true,
    title: String? = ""
) {
    includedToolbarBinding.toolbarTitle.text = title
    setSupportActionBar(includedToolbarBinding.toolbar)
    supportActionBar?.run {
        setDisplayHomeAsUpEnabled(showHome)
        setDisplayShowHomeEnabled(showHome)
    }
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
