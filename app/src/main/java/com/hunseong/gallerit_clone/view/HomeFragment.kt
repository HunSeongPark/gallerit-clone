package com.hunseong.gallerit_clone.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.google.android.material.tabs.TabLayoutMediator
import com.hunseong.gallerit_clone.R
import com.hunseong.gallerit_clone.databinding.FragmentGalleryBinding
import com.hunseong.gallerit_clone.databinding.FragmentHomeBinding
import com.hunseong.gallerit_clone.databinding.FragmentMyBinding
import com.hunseong.gallerit_clone.view.adapter.HomePagerAdapter
import com.hunseong.gallerit_clone.view.adapter.HomePagerAdapter.Companion.IMAGE_LIST_PAGE_INDEX
import com.hunseong.gallerit_clone.view.adapter.HomePagerAdapter.Companion.MY_PAGE_INDEX
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(inflater, container, false)

        setViewPager()
        setTabLayout()
        setToolbar()
        return binding.root
    }

    private fun setViewPager() = with(binding) {
        viewPager.adapter = HomePagerAdapter(this@HomeFragment)
    }

    // 기존 getIcon, getText로 나누어져 있던 메소드를 Pair 형태의 getIconAndText 메소드로 합침
    // 코드 리팩토링 방식에서 이러한 리팩토링이 더 효율적인지에 대한 고민 필요
    private fun setTabLayout() = with(binding) {
        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            val iconAndText = getTabIconAndText(position)
            tab.setIcon(iconAndText.first)
            tab.setText(iconAndText.second)
        }.attach()
    }

    private fun getTabIconAndText(position: Int): Pair<Int, Int> {
        return when(position) {
            IMAGE_LIST_PAGE_INDEX -> R.drawable.image_tab_selector to R.string.images
            MY_PAGE_INDEX -> R.drawable.my_tab_selector to R.string.my
            else -> throw IndexOutOfBoundsException()
        }
    }

    private fun setToolbar() {
        binding.toolbar.title = ""
        (activity as MainActivity).setSupportActionBar(binding.toolbar)
    }
}