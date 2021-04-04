package com.armanco.integral.navigation.category

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.armanco.integral.R
import com.armanco.integral.models.Category
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_category.*

@AndroidEntryPoint
class CategoryFragment: Fragment(R.layout.fragment_category) {
    private val model: CategoryViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        categoryListView?.onCardClick = {
            navigateToItem(it)
        }
        model.load()
        model.categories.observe(viewLifecycleOwner) { categories ->
            categoryListView?.with(categories)
        }
    }

    private fun navigateToItem(category: Category) {

    }
}