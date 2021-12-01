package com.hunseong.gallerit_clone.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.hunseong.gallerit_clone.data.model.RedditImage
import com.hunseong.gallerit_clone.data.model.RedditImages
import com.hunseong.gallerit_clone.databinding.ItemGalleryBinding
import com.hunseong.gallerit_clone.extensions.load
import timber.log.Timber

class GalleryPagerAdapter(private val onClick: (RedditImage) -> Unit) :
    RecyclerView.Adapter<GalleryPagerAdapter.ViewHolder>() {

    var images: Array<RedditImage> = emptyArray()

    inner class ViewHolder(private val binding: ItemGalleryBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(image: RedditImage) {
            binding.iv.setOnClickListener {
                onClick(image)
            }
            binding.iv.load(image.url)

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(ItemGalleryBinding.inflate(LayoutInflater.from(parent.context),
            parent,
            false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(images[position])
    }

    override fun getItemCount(): Int = images.size
}