package com.armanco.integral.utils.extensions

import androidx.core.content.ContextCompat
import com.armanco.integral.R
import com.armanco.integral.data.models.PlotEntries
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.components.Legend
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet

fun LineChart.plot(plotEntries: PlotEntries?) {
    val lineDataSet = LineDataSet(plotEntries?.entries, context?.getString(R.string.function)).apply {
        setDrawIcons(false)
        color = ContextCompat.getColor(context, R.color.primary)
        lineWidth = 2f
        setDrawCircles(false)
        setDrawCircleHole(false)
        setDrawFilled(false)
        formLineWidth = 1f
        formSize = 15f
        setDrawValues(false)
        setDrawHighlightIndicators(false)
        valueTextColor = ContextCompat.getColor(context, R.color.primaryDarker)
        mode = LineDataSet.Mode.CUBIC_BEZIER
    }
    val lineDataSetIntegral = LineDataSet(plotEntries?.entriesIntegral, context?.getString(R.string.integral)).apply {
        setDrawIcons(false)
        color = ContextCompat.getColor(context, R.color.primaryDarker)
        lineWidth = 2f
        setDrawCircles(false)
        setDrawCircleHole(false)
        setDrawFilled(false)
        formLineWidth = 1f
        formSize = 15f
        setDrawValues(false)
        setDrawHighlightIndicators(false)
        valueTextColor = ContextCompat.getColor(context, R.color.primaryDarker)
        mode = LineDataSet.Mode.CUBIC_BEZIER
    }
    data = LineData(listOf(lineDataSet, lineDataSetIntegral))
    fitScreen()
    isDragDecelerationEnabled = false
    description.isEnabled = false
    legend.isEnabled = true
    legend.verticalAlignment = Legend.LegendVerticalAlignment.TOP
    legend.horizontalAlignment = Legend.LegendHorizontalAlignment.RIGHT
    axisRight.isEnabled = false
    xAxis.position = XAxis.XAxisPosition.BOTTOM
    setScaleEnabled(false)
    invalidate()
}
