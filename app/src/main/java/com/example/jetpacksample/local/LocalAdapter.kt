package com.example.jetpacksample.local

import android.graphics.Typeface
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.jetpacksample.R

class LocalAdapter : PagingDataAdapter<Words, LocalViewHolder>(diffCallback) {
    override fun onBindViewHolder(holder: LocalViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LocalViewHolder {
        return LocalViewHolder(parent)
    }


    companion object {
        val diffCallback = object : DiffUtil.ItemCallback<Words>() {
            override fun areItemsTheSame(oldItem: Words, newItem: Words) =
                oldItem.name == newItem.name

            override fun areContentsTheSame(oldItem: Words, newItem: Words) =
                oldItem == newItem
        }
    }
}

class LocalViewHolder(parent: ViewGroup) : RecyclerView.ViewHolder(
    LayoutInflater.from(parent.context).inflate(R.layout.row_local, parent, false)
) {
    var word:Words? = null
    private val nameView = itemView.findViewById<TextView>(R.id.name)

    fun bind(item: Words?) {
        item?.let {
            nameView?.text = it.name
            word = it
        }
    }
}