package com.armanco.integral.navigation.solver

import android.os.Bundle
import android.view.View
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.armanco.integral.R
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_solver.*

@AndroidEntryPoint
class SolverFragment: Fragment(R.layout.fragment_solver) {
    private val model: SolverViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        model.load()
        initValues()
        calculate?.setOnClickListener {
            model.calculate()
        }
        function?.addTextChangedListener {
            model.function.value = it?.toString()
        }
        lowerLimit?.addTextChangedListener {
            model.lowerLimit.value = it?.toString()?.toDoubleOrNull() ?: 0.0
        }
        upperLimit?.addTextChangedListener {
            model.upperLimit.value = it?.toString()?.toDoubleOrNull() ?: 1.0
        }
        steps?.addTextChangedListener {
            model.steps.value = it?.toString()?.toIntOrNull() ?: 1
        }
        trapezoidal?.setOnCheckedChangeListener { _, isChecked ->
            if(isChecked) model.setMethod(SolverViewModel.Companion.Method.Trapezoidal)
        }
        simpson?.setOnCheckedChangeListener { _, isChecked ->
            if(isChecked) model.setMethod(SolverViewModel.Companion.Method.Simpson)
        }
        romberg?.setOnCheckedChangeListener { _, isChecked ->
            if(isChecked) model.setMethod(SolverViewModel.Companion.Method.Romberg)
        }
        model.hasSteps.observe(viewLifecycleOwner) { hasSteps ->
            steps?.visibility = if(hasSteps) View.VISIBLE else View.GONE
            stepsTitle?.visibility = if(hasSteps) View.VISIBLE else View.GONE
        }
        model.result.observe(viewLifecycleOwner) { res ->
            result?.text = res?.toString()
        }
    }

    private fun initValues() {
        function?.setText(model.function.value)
        lowerLimit?.setText(model.lowerLimit.value.toString())
        upperLimit?.setText(model.upperLimit.value.toString())
        steps?.setText(model.steps.value.toString())
        when(model.method.value) {
            SolverViewModel.Companion.Method.Trapezoidal -> trapezoidal?.isChecked = true
            SolverViewModel.Companion.Method.Simpson -> simpson?.isChecked = true
            SolverViewModel.Companion.Method.Romberg -> romberg?.isChecked = true
        }
    }
}