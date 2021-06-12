package com.armanco.integral.ui.navigation.plot

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.armanco.integral.ui.navigation.plot.page.PlotChartViewPager
import com.armanco.integral.ui.navigation.plot.page.PlotFormViewPager


class PlotAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {

    var onPlot: (()->Unit)? = null

    override fun getItemCount(): Int = 2

    override fun createFragment(position: Int): Fragment {
        return when(position) {
            0 -> PlotFormViewPager().apply {
                this.onPlot = this@PlotAdapter.onPlot
            }
            else -> PlotChartViewPager()
        }
    }
}
