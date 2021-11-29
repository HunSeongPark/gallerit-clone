package com.hunseong.gallerit_clone.view

import android.os.Bundle
import android.view.*
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.hunseong.gallerit_clone.R
import com.hunseong.gallerit_clone.databinding.FragmentImageListBinding
import com.hunseong.gallerit_clone.view.adapter.RedditImageAdapter
import com.hunseong.gallerit_clone.viewmodel.ImageListViewModel
import dagger.hilt.android.AndroidEntryPoint

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

        binding.apply {
            lifecycleOwner = this@ImageListFragment
            vm = viewModel
            adapter = imageAdapter
        }

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
}