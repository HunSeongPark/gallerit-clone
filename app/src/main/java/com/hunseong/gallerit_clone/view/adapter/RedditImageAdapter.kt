package com.hunseong.gallerit_clone.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.hunseong.gallerit_clone.data.model.RedditImage
import com.hunseong.gallerit_clone.databinding.ItemImageBinding
import com.hunseong.gallerit_clone.extensions.loadWithThumbnail

class RedditImageAdapter(private val onClick: (Int) -> Unit) :
    ListAdapter<RedditImage, RedditImageAdapter.RedditImageViewHolder>(diffUtil) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RedditImageViewHolder {
        val binding = ItemImageBinding.inflate(LayoutInflater.from(parent.context),
            parent, false)

        return RedditImageViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RedditImageViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class RedditImageViewHolder(private val binding: ItemImageBinding) :
        RecyclerView.ViewHolder(binding.root) {

        init {
            binding.root.setOnClickListener {
                onClick(adapterPosition)
            }
        }

        fun bind(image: RedditImage) {
            binding.iv.loadWithThumbnail(image.url)
        }
    }

    companion object {
        private val diffUtil = object : DiffUtil.ItemCallback<RedditImage>() {

            override fun areItemsTheSame(oldItem: RedditImage, newItem: RedditImage): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: RedditImage, newItem: RedditImage): Boolean {
                return oldItem == newItem
            }

        }
    }
}