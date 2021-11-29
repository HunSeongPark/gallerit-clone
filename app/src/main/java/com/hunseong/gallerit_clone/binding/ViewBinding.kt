package com.hunseong.gallerit_clone.binding

import android.view.View
import android.widget.TextView
import androidx.constraintlayout.widget.Group
import androidx.core.view.isVisible
import androidx.databinding.BindingAdapter
import com.hunseong.gallerit_clone.R
import com.hunseong.gallerit_clone.data.model.Result

object ViewBinding {

    @JvmStatic
    @BindingAdapter("isVisible")
    fun bindIsVisible(group: Group, result: Result) {
        when (result) {
            is Result.Success<*> -> group.isVisible = false
            is Result.Empty -> {
                group.isVisible = true
                group.rootView.findViewById<TextView>(R.id.empty_tv).text =
                    group.context.getString(R.string.empty_result)
            }
            is Result.Error -> {
                group.isVisible = true
                group.rootView.findViewById<TextView>(R.id.empty_tv).text =
                    if (result.isNetworkError) {
                        group.context.getString(R.string.no_internet)
                    } else {
                        group.context.getString(R.string.empty_result)
                    }

            }
            else -> Unit
        }
    }

    @JvmStatic
    @BindingAdapter("isLoading")
    fun bindIsLoading(view: View, result: Result) {
        view.visibility = when (result) {
            is Result.Loading -> View.VISIBLE
            else -> View.GONE
        }
    }
}