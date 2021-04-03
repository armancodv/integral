package com.armanco.integral.navigation.category

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.armanco.integral.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CategoryFragment: Fragment(R.layout.fragment_list) {
    private val model: CategoryViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        model.load()
        model.categories.observe(viewLifecycleOwner) { categories ->
        }
    }
}