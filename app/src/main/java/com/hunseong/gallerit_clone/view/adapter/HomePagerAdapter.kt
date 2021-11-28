package com.hunseong.gallerit_clone.view.adapter

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.hunseong.gallerit_clone.view.ImageListFragment
import com.hunseong.gallerit_clone.view.MyFragment
import java.lang.IndexOutOfBoundsException

class HomePagerAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {


    // ViewPager에 사용할 fragment 생성 block을 position과 map으로 묶음
    // 새로운 fragment 추가 시 fragmentCreator에 추가하면 override fun에 모두 적용됨 (유지보수 용이)
   private val fragmentCreator: Map<Int, () -> Fragment> = mapOf(
        IMAGE_LIST_PAGE_INDEX to { ImageListFragment() },
        MY_PAGE_INDEX to { MyFragment() },
    )

    override fun getItemCount() = fragmentCreator.size

    override fun createFragment(position: Int): Fragment {
        return fragmentCreator[position]?.invoke() ?: throw IndexOutOfBoundsException()
    }

    companion object {
        const val IMAGE_LIST_PAGE_INDEX = 0
        const val MY_PAGE_INDEX = 1
    }
}