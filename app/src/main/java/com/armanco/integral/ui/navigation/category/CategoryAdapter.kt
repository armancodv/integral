package com.armanco.integral.ui.navigation.category

import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.armanco.integral.data.models.Keys
import com.armanco.integral.data.models.Section
import com.armanco.integral.ui.navigation.category.page.CategoryViewPager

class CategoryAdapter(fragment: Fragment, val sections: List<Section>) : FragmentStateAdapter(fragment) {


    override fun getItemCount(): Int = sections.size

    override fun createFragment(position: Int): Fragment {
        return CategoryViewPager().apply { arguments = bundleOf( Keys.SECTION to sections.getOrNull(position)?.ordinal) }
    }
}
