package com.armanco.integral.ui.navigation.category

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.armanco.integral.R
import com.armanco.integral.data.models.Section
import com.armanco.integral.utils.extensions.isTrigonometryOrTrigonometryPersian
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_category.*

@AndroidEntryPoint
class CategoryFragment: Fragment(R.layout.fragment_category) {
    private val model: CategoryViewModel by activityViewModels()
    private var adapter: CategoryAdapter? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val sections = if(isTrigonometryOrTrigonometryPersian) {
            listOf( Section.TRIGONOMETRY, Section.INTEGRAL )
        } else {
            listOf( Section.INTEGRAL, Section.TRIGONOMETRY )
        }
        adapter = CategoryAdapter(this, sections)
        viewPager?.adapter = adapter
        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            tab.text = when(sections.getOrNull(position)) {
                Section.TRIGONOMETRY -> context?.getString(R.string.trigonometry)
                else -> context?.getString(R.string.integral)
            }
        }.attach()
        model.load()
    }

}