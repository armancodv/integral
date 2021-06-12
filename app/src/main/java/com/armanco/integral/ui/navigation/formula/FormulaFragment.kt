package com.armanco.integral.ui.navigation.formula

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.armanco.integral.R
import com.armanco.integral.data.models.Formula
import com.armanco.integral.ui.navigation.image.ImageFragment
import com.armanco.integral.ui.navigation.image.ImageFragment.Companion.IMAGE_KEY
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_formula.*

@AndroidEntryPoint
class FormulaFragment: Fragment(R.layout.fragment_formula) {
    private val model: FormulaViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        formulaListView?.onCardClick = {
            navigateToImage(it)
        }
        model.load(arguments?.getInt(ID_KEY))
        model.formulas.observe(viewLifecycleOwner) { formulas ->
            Log.d("formula", formulas.toString())
            formulaListView?.with(formulas)
        }
    }

    private fun navigateToImage(formula: Formula) {
        model.selectImage(formula.id, formula.categoryId, getString(formula.stringRes))
        findNavController().navigate(R.id.action_formulaFragment_to_imageFragment,
            Bundle().apply {
                putInt(IMAGE_KEY, formula.drawableRes)
                putString(ImageFragment.TITLE_KEY, getString(formula.stringRes))
            })
    }

    companion object {
        const val ID_KEY = "id"
        const val TITLE_KEY = "title"
    }

}