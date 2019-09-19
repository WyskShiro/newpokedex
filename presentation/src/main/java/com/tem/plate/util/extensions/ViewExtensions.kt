package com.tem.plate.util.extensions

import android.graphics.drawable.Drawable
import android.text.Editable
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.core.content.ContextCompat
import androidx.databinding.ViewDataBinding
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.google.android.material.textfield.TextInputLayout
import com.tem.plate.R
import com.ufms.mediadorpedagogico.presentation.util.SimpleTextWatcher
import io.reactivex.disposables.Disposable
import io.reactivex.subjects.PublishSubject


// TextInputLayout
fun TextInputLayout.getText(): String? {
    return editText?.toString()
}

fun TextInputLayout.observeChanges(callback: (String) -> Unit) {
    editText?.observeChanges(callback)
}

fun TextInputLayout.setError(@StringRes messageId: Int?) {
    messageId?.let {
        error = context.getString(messageId)
        if (editText == null) return
        val hasErrorWatcher: Boolean? = getTag(R.id.has_error_watcher) as? Boolean
        if (hasErrorWatcher == null || !hasErrorWatcher) {
            editText?.addTextChangedListener(object : SimpleTextWatcher() {
                override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {
                    error = null
                    editText?.removeTextChangedListener(this)
                    setTag(R.id.has_error_watcher, false)
                }
            })
            setTag(R.id.has_error_watcher, true)
        }
        return
    }
    error = null
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

// using drawables with glide loader is required
// glide 4.0 can't com.tem.plate.util.extensions.load vectors properly
// more recent versions (latest is 4.2) have issues merging dex files and throw exceptions at runtime
private fun placeholderOptions(placeholder: Drawable? = null): RequestOptions {
    val requestOptions = RequestOptions()
    placeholder?.let { requestOptions.placeholder(it).error(it) }
    return requestOptions
}