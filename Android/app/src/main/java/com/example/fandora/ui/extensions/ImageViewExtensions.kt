package com.example.fandora.ui.extensions

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.example.fandora.R

fun ImageView.load(url: String?) {
    if (!url.isNullOrEmpty()) {
        Glide.with(this)
            .load(url)
            .placeholder(R.color.gray_50)
            .error(R.color.gray_50)
            .into(this)
    } else {
        setBackgroundResource(R.color.gray_50)
    }
}