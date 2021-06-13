package com.armanco.integral.ui.navigation.category.page

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.armanco.integral.R
import com.armanco.integral.data.models.Category
import com.armanco.integral.data.models.Keys
import com.armanco.integral.data.models.Section
import com.armanco.integral.ui.navigation.category.CategoryViewModel
import com.armanco.integral.ui.navigation.formula.FormulaFragment
import kotlinx.android.synthetic.main.view_pager_category.*

class CategoryViewPager: Fragment(R.layout.view_pager_category) {
    private val model: CategoryViewModel by activityViewModels()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val section = enumValues<Section>()[arguments?.getInt(Keys.SECTION, 0) ?: 0]
        model.loadSection(section)
        categoryListView?.onCardClick = {
            navigateToItem(it)
        }
        when(section) {
            Section.INTEGRAL -> model.categoriesIntegral
            Section.TRIGONOMETRY -> model.categoriesTrigonometry
        }.observe(viewLifecycleOwner) { categories ->
            categoryListView?.with(categories)
        }
    }

    private fun navigateToItem(category: Category) {
        model.selectCategory(category.id, getString(category.stringRes))
        Log.d("category", category.toString())
        findNavController().navigate(R.id.action_categoryFragment_to_formulaFragment,
            Bundle().apply {
                putInt(FormulaFragment.ID_KEY, category.id)
                putString(FormulaFragment.TITLE_KEY, getString(category.stringRes))
            })
    }

}