package com.tem.plate.util.extensions

import android.graphics.drawable.Drawable
import android.text.Editable
import android.view.View
import android.widget.TextView
import com.bumptech.glide.request.RequestOptions
import com.google.android.material.textfield.TextInputLayout
import com.tem.plate.util.resources.SimpleTextWatcher
import io.reactivex.disposables.Disposable
import io.reactivex.subjects.PublishSubject


// TextInputLayout
fun TextInputLayout.getText(): String? {
    return editText?.toString()
}

fun TextInputLayout.observeChanges(callback: (String) -> Unit) {
    editText?.observeChanges(callback)
}

// TextView
fun TextView.observeChanges(callback: (String) -> Unit): Disposable? {
    val subject = PublishSubject.create<String>()
    addTextChangedListener(object : SimpleTextWatcher() {
        override fun afterTextChanged(s: Editable) {
            subject.onNext(s.toString())
        }
    })
    return subject.subscribe { callback(it) }
}

//View
fun View.setOnClickListener(callback: () -> Unit) {
    this.setOnClickListener { callback.invoke() }
}


// views

fun View.setVisible(visible: Boolean) {
    visibility = if (visible) View.VISIBLE else View.GONE
}

fun View.isVisible(): Boolean {
    return visibility == View.VISIBLE
}