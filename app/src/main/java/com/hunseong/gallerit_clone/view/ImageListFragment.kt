package com.hunseong.gallerit_clone.view

import android.os.Bundle
import android.view.*
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.hunseong.gallerit_clone.R
import com.hunseong.gallerit_clone.data.model.RedditImage
import com.hunseong.gallerit_clone.databinding.FragmentImageListBinding
import com.hunseong.gallerit_clone.view.adapter.RedditImageAdapter
import com.hunseong.gallerit_clone.viewmodel.ImageListViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@AndroidEntryPoint
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
        setImageRecyclerView()
        setSwipeRefreshLayout()
        setHasOptionsMenu(true)

        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_images, menu)
        setSearchView(menu)
    }

    private fun setSearchView(menu: Menu) {
        val searchItem = menu.findItem(R.id.action_search)

        (searchItem.actionView as SearchView).apply {
            queryHint = getString(R.string.search_hint)

            setOnQueryTextListener(object : SearchView.OnQueryTextListener {

                override fun onQueryTextSubmit(p0: String?): Boolean {
                    return false
                }

                override fun onQueryTextChange(p0: String): Boolean {
                    viewModel.setQuery(p0)
                    return true
                }

            })
        }
    }

    private fun setImagesObserver() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.images.collect { result ->
                    when (result) {
                        is com.hunseong.gallerit_clone.data.model.Result.Loading -> {
                            binding.swipeLayout.isRefreshing = true
                        }
                        is com.hunseong.gallerit_clone.data.model.Result.Success -> {
                            showImagesRecyclerView(result.data)
                        }
                        is com.hunseong.gallerit_clone.data.model.Result.Empty -> {
                            val message = getString(R.string.empty_result)
                            showEmptyView(message)
                        }
                        is com.hunseong.gallerit_clone.data.model.Result.Error -> {
                            val message = if (result.isNetworkError) {
                                getString(R.string.no_internet)
                            } else {
                                getString(R.string.empty_result)
                            }
                            showEmptyView(message)
                        }
                    }
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

    private fun showEmptyView(message: String) {
        with(binding) {
            swipeLayout.isRefreshing = false

            recyclerView.visibility = View.GONE
            emptyGroup.visibility = View.VISIBLE

            emptyTv.text = message
        }
    }

    private fun setImageRecyclerView() {
        binding.recyclerView.apply {
            adapter = imageAdapter
        }
    }

    private fun setSwipeRefreshLayout() {
        binding.swipeLayout.isEnabled = false
    }
}