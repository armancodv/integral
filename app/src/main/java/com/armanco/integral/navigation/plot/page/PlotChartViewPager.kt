package com.armanco.integral.navigation.plot.page

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.armanco.integral.R
import com.armanco.integral.navigation.plot.PlotViewModel
import com.armanco.integral.utils.extensions.plot
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.view_pager_plot_chart.*

@AndroidEntryPoint
class PlotChartViewPager: Fragment(R.layout.view_pager_plot_chart) {
    private val model: PlotViewModel by activityViewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        model.plotEntries.observe(viewLifecycleOwner) {
            plot?.plot(it)
        }
    }
}