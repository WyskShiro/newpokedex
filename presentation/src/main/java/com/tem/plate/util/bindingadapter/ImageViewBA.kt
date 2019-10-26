package com.tem.plate.util.bindingadapter

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions

@BindingAdapter("load")
fun ImageView.load(url: String?) {
    url?.let {
        Glide.with(context).load(it).into(this)
    }
}

@BindingAdapter("loadBordered")
fun ImageView.loadBordered(url: String?) {
    url?.let {
        val options = RequestOptions().transform(RoundedCorners(DEFAULT_BORDER_RADIUS))
        Glide.with(context).load(it).apply(options).into(this)
    }
}

private const val DEFAULT_BORDER_RADIUS = 8