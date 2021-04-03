package com.armanco.integral.navigation.list

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.armanco.integral.R

class ListFragment: Fragment(R.layout.fragment_list) {
    private val model: ListViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        model.load()
        model.categories.observe(viewLifecycleOwner) { categories ->
        }
    }
}