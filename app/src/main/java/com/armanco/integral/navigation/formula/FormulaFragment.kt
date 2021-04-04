package com.armanco.integral.navigation.formula

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.armanco.integral.R
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_formula.*

@AndroidEntryPoint
class FormulaFragment: Fragment(R.layout.fragment_formula) {
    private val model: FormulaViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        model.load(arguments?.getInt(ID_KEY))
        model.formulas.observe(viewLifecycleOwner) { formulas ->
            Log.d("formula", formulas.toString())
            formulaListView?.with(formulas)
        }
    }

    companion object {
        const val ID_KEY = "id"
    }

}