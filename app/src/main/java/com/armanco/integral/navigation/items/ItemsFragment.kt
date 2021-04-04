package com.armanco.integral.navigation.items

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.armanco.integral.R

class ItemsFragment: Fragment(R.layout.fragment_item) {
    private val model: ItemsViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        model.load(arguments?.getString(ID_KEY))
        model.items.observe(viewLifecycleOwner) { items ->
        }
    }

    companion object {
        const val ID_KEY = "id"
    }

}