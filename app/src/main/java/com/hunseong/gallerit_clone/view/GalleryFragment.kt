package com.hunseong.gallerit_clone.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.hunseong.gallerit_clone.databinding.FragmentGalleryBinding
import com.hunseong.gallerit_clone.view.adapter.GalleryPagerAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class GalleryFragment : Fragment() {
    private lateinit var binding: FragmentGalleryBinding
    private val pagerAdapter: GalleryPagerAdapter by lazy {
        GalleryPagerAdapter()
    }

    private val args: GalleryFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentGalleryBinding.inflate(inflater, container, false)

        initViews()
        setFullscreen()

        return binding.root
    }

    override fun onDestroy() {
        super.onDestroy()
        clearFullscreen()
    }

    private fun initViews() = with(binding) {
        viewPager.adapter = pagerAdapter
        images = args.images
        position = args.position
    }

    private fun setFullscreen() {
        activity?.window?.addFlags(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN)
    }

    private fun clearFullscreen() {
        activity?.window?.clearFlags(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN)
    }
}