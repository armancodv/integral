package com.armanco.integral.navigation.plot

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.armanco.integral.R
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_plot.*

@AndroidEntryPoint
class PlotFragment: Fragment(R.layout.fragment_plot) {
    private val model: PlotViewModel by activityViewModels()
    private var adapter: PlotAdapter? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapter = PlotAdapter(this)
        adapter?.onPlot = {
            viewPager?.currentItem = 1
        }
        viewPager?.adapter = adapter
        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            tab.text = when(position) {
                0 -> context?.getString(R.string.form)
                else -> context?.getString(R.string.chart)
            }
        }.attach()
        model.load()
        model.plot()
    }
}