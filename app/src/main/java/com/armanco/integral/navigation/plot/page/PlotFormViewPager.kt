package com.armanco.integral.navigation.plot.page

import android.os.Bundle
import android.view.View
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.armanco.integral.R
import com.armanco.integral.navigation.plot.PlotViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.view_pager_plot_form.*

@AndroidEntryPoint
class PlotFormViewPager: Fragment(R.layout.view_pager_plot_form) {
    private val model: PlotViewModel by activityViewModels()

    var onPlot: (()->Unit)? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        function?.setText(model.function.value)
        lowerLimit?.setText(model.lowerLimit.value.toString())
        upperLimit?.setText(model.upperLimit.value.toString())
        steps?.setText(model.steps.value.toString())
        function?.addTextChangedListener {
            model.function.postValue(it.toString())
        }
        lowerLimit?.addTextChangedListener {
            model.lowerLimit.postValue(it.toString().toDoubleOrNull())
        }
        upperLimit?.addTextChangedListener {
            model.upperLimit.postValue(it.toString().toDoubleOrNull())
        }
        steps?.addTextChangedListener {
            model.steps.postValue(it.toString().toIntOrNull())
        }
        plotButton?.setOnClickListener {
            model.plot()
            onPlot?.invoke()
        }
    }
}