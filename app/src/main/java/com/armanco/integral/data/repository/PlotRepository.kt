package com.armanco.integral.data.repository

import com.github.mikephil.charting.data.Entry
import com.mathlibrary.function.FunctionX
import javax.inject.Inject

class PlotRepository @Inject constructor(
    private val solverRepository: SolverRepository
) {

    fun value(f: String, x: Double): Double {
        val function = FunctionX(f, false)
        return function.getF_xo(x)
    }

    fun entries(f: String, lowerLimit: Double, upperLimit: Double, steps: Int): List<Entry> {
        val entries = mutableListOf<Entry>()
        val dx = (upperLimit - lowerLimit) / steps.toDouble()
        for (i in 0..steps) {
            val x = dx * i.toDouble() + lowerLimit
            entries.add(Entry(x.toFloat(), value(f, x).toFloat()))
        }
        return entries
    }

    fun entriesIntegral(f: String, lowerLimit: Double, upperLimit: Double, steps: Int): List<Entry> {
        val entries = mutableListOf<Entry>()
        val dx = (upperLimit - lowerLimit) / steps.toDouble()
        for (i in 0..steps) {
            val x = dx * i.toDouble() + lowerLimit
            solverRepository.romberg(f, 0.0, x, 10)?.let {
                entries.add(Entry(x.toFloat(), it.toFloat()))
            }
        }
        return entries
    }

}
