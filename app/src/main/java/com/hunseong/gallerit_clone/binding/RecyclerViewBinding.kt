package com.hunseong.gallerit_clone.binding

import android.view.View
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.hunseong.gallerit_clone.data.model.RedditImages
import com.hunseong.gallerit_clone.data.model.Result

object RecyclerViewBinding {

    @JvmStatic
    @BindingAdapter("adapter")
    fun bindAdapter(view: RecyclerView, adapter: RecyclerView.Adapter<*>) {
        view.adapter = adapter
    }

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
}