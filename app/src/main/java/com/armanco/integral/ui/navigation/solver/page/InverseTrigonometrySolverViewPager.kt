package com.armanco.integral.ui.navigation.solver.page

import android.os.Bundle
import android.view.View
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.armanco.integral.R
import com.armanco.integral.data.models.AngleUnit
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.view_pager_inverse_trigonometry_solver.*

@AndroidEntryPoint
class InverseTrigonometrySolverViewPager: Fragment(R.layout.view_pager_inverse_trigonometry_solver) {
    private val model: InverseTrigonometrySolverViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        model.load()
        model.asin.observe(viewLifecycleOwner) {
            asinScreen?.result = it.toString()
        }
        model.acos.observe(viewLifecycleOwner) {
            acosScreen?.result = it.toString()
        }
        model.atan.observe(viewLifecycleOwner) {
            atanScreen?.result = it.toString()
        }
        model.acot.observe(viewLifecycleOwner) {
            acotScreen?.result = it.toString()
        }
        model.acsc.observe(viewLifecycleOwner) {
            acscScreen?.result = it.toString()
        }
        model.asec.observe(viewLifecycleOwner) {
            asecScreen?.result = it.toString()
        }
        toggleGroup.isSelectionRequired = true
        arc?.addTextChangedListener {
            model.changeArc(it.toString().toDoubleOrNull())
        }
        degree?.setOnClickListener {
            model.changeUnit(AngleUnit.DEGREE)
        }
        radian?.setOnClickListener {
            model.changeUnit(AngleUnit.RADIAN)
        }
    }
}