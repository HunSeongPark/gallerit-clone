package com.hunseong.gallerit_clone.binding

import android.view.View
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.hunseong.gallerit_clone.data.model.RedditImage
import com.hunseong.gallerit_clone.data.model.RedditImages
import com.hunseong.gallerit_clone.data.model.Result
import com.hunseong.gallerit_clone.view.adapter.GalleryPagerAdapter
import com.hunseong.gallerit_clone.view.adapter.HomePagerAdapter
import timber.log.Timber

object RecyclerViewBinding {

    @JvmStatic
    @BindingAdapter("submitList")
    fun bindSubmitList(view: RecyclerView, result: Result) {
        when (result) {
            is Result.Success<*> -> {
                view.visibility = View.VISIBLE
                (view.adapter as ListAdapter<Any, *>).submitList(result.data as RedditImages)
            }
            is Result.Loading -> Unit
            else -> {
                view.visibility = View.GONE
            }
        }
    }

    @JvmStatic
    @BindingAdapter("submitListViewPager", "currentPosition")
    fun bindSubmitListViewPager(view: ViewPager2, list: Array<RedditImage>, position: Int) {
        (view.adapter as GalleryPagerAdapter).apply {
            images = list
            notifyDataSetChanged()
        }
        view.setCurrentItem(position, false)
    }
}