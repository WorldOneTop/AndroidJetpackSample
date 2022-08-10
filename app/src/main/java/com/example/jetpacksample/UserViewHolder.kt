package com.example.jetpacksample

import android.util.Log
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.example.jetpacksample.databinding.RowPagingBinding


class UserViewHolder(private val binding: RowPagingBinding) : RecyclerView.ViewHolder(binding.root) {
    var user:User? = null

    fun bind(item: User?) {
        item?.let {
            binding.id.text = "id : ${it.id}"
            binding.name.text = "${it.firstName} ${it.lastName}"
            binding.email.text = it.email
            Glide.with(binding.avatar.context)
                .load(it.avatar)
                .transition(DrawableTransitionOptions.withCrossFade())
                .into(binding.avatar)
            user = item
        }
    }
}