package com.armanco.integral.ui.navigation.solver.page

import android.os.Bundle
import android.view.View
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.armanco.integral.R
import com.armanco.integral.data.models.AngleUnit
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.view_pager_trigonometry_solver.*

@AndroidEntryPoint
class TrigonometrySolverViewPager: Fragment(R.layout.view_pager_trigonometry_solver) {
    private val model: TrigonometrySolverViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        model.load()
        model.sin.observe(viewLifecycleOwner) {
            sinScreen?.result = it.toString()
        }
        model.cos.observe(viewLifecycleOwner) {
            cosScreen?.result = it.toString()
        }
        model.tan.observe(viewLifecycleOwner) {
            tanScreen?.result = it.toString()
        }
        model.cot.observe(viewLifecycleOwner) {
            cotScreen?.result = it.toString()
        }
        model.csc.observe(viewLifecycleOwner) {
            cscScreen?.result = it.toString()
        }
        model.sec.observe(viewLifecycleOwner) {
            secScreen?.result = it.toString()
        }
        toggleGroup.isSelectionRequired = true
        angle?.addTextChangedListener {
            model.changeAngle(it.toString().toDoubleOrNull())
        }
        degree?.setOnClickListener {
            model.changeUnit(AngleUnit.DEGREE)
        }
        radian?.setOnClickListener {
            model.changeUnit(AngleUnit.RADIAN)
        }
    }
}