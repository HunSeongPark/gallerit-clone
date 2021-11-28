package com.hunseong.gallerit_clone.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.hunseong.gallerit_clone.R
import com.hunseong.gallerit_clone.data.model.RedditImage
import com.hunseong.gallerit_clone.databinding.FragmentImageListBinding
import com.hunseong.gallerit_clone.view.adapter.RedditImageAdapter
import com.hunseong.gallerit_clone.viewmodel.ImageListViewModel

class ImageListFragment : Fragment() {

    private lateinit var binding: FragmentImageListBinding
    private val viewModel: ImageListViewModel by viewModels()
    private val imageAdapter: RedditImageAdapter by lazy {
        RedditImageAdapter()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentImageListBinding.inflate(inflater, container, false)

        setImagesObserver()

        return binding.root
    }

    private fun setImagesObserver() {
        viewModel.images.observe(viewLifecycleOwner) { result ->
            when (result) {
                is com.hunseong.gallerit_clone.data.model.Result.Loading -> {
                    // Set refreshing state
                    binding.swipeLayout.isRefreshing = true
                }
                is com.hunseong.gallerit_clone.data.model.Result.Success -> {
                    showImagesRecyclerView(result.data)
                }
                is com.hunseong.gallerit_clone.data.model.Result.Empty -> {
                    val message = getString(R.string.no_image_show)
                    showEmptyView(message)
                }
                is com.hunseong.gallerit_clone.data.model.Result.Error -> {
                    val message = if (result.isNetworkError) {
                        getString(R.string.no_internet)
                    } else {
                        getString(R.string.no_image_show)
                    }
                    showEmptyView(message)
                }
            }
        }
    }

    private fun showImagesRecyclerView(images: List<RedditImage>) {
        with(binding) {
            swipeLayout.isRefreshing = false

            recyclerView.visibility = View.VISIBLE
            emptyGroup.visibility = View.GONE
        }
        imageAdapter.submitList(images)
    }

    // todo observe부터.
}