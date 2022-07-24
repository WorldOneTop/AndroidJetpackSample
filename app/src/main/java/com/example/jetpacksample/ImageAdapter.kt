package com.example.jetpacksample

import android.view.View
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions

@BindingAdapter("imageFromUrl")
fun bindImageFromUrl(view: ImageView, avatar: String?) {
    if (!avatar.isNullOrEmpty()) {
        view.visibility = View.VISIBLE
        Glide.with(view.context)
            .load(avatar)
            .transition(DrawableTransitionOptions.withCrossFade())
            .into(view)
    }else{
        view.visibility = View.GONE
    }
}
