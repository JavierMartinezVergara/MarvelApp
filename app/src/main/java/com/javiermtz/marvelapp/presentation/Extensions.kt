package com.javiermtz.marvelapp.presentation

import android.widget.ImageView
import coil.load
import com.bumptech.glide.Glide

fun ImageView.toLoad(path: String) {
  Glide.with(this)
    .load(path)
    .into(this)
}
