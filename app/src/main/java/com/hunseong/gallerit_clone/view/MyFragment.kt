package com.hunseong.gallerit_clone.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.hunseong.gallerit_clone.databinding.FragmentMyBinding
import com.hunseong.gallerit_clone.view.adapter.MyAdapter
import com.hunseong.gallerit_clone.viewmodel.MyViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MyFragment : Fragment() {

    private lateinit var binding: FragmentMyBinding

    private val viewModel: MyViewModel by viewModels()

    private val myAdapter: MyAdapter by lazy {
        MyAdapter { position ->
            val images = myAdapter.currentList.toTypedArray()
            val directions =
                HomeFragmentDirections.homeFragmentToGalleryFragment(images, position)

            findNavController().navigate(directions)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentMyBinding.inflate(inflater, container, false)
        binding.apply {
            lifecycleOwner = viewLifecycleOwner
            vm = viewModel
            recyclerView.adapter = myAdapter
        }

        return binding.root
    }
}