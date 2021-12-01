package com.hunseong.gallerit_clone.extensions

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions.withCrossFade

fun ImageView.load(uri: String?) {
    Glide.with(context)
        .load(uri)
        .transition(withCrossFade())
        .into(this)
}

fun ImageView.loadWithThumbnail(uri: String?, sizeMultiplier: Float = 0.25f) {
    Glide.with(context)
        .load(uri)
        .thumbnail(sizeMultiplier)
        .transition(withCrossFade())
        .into(this)
}