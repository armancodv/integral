package com.armanco.integral.data.repository

import com.mathlibrary.exception.CalculatorException
import com.mathlibrary.integral.Integral
import javax.inject.Inject

class SolverRepository @Inject constructor() {

    @Throws(CalculatorException::class)
    fun trapezoidal(f: String, lowerLimit: Double, upperLimit: Double): Double? {
        val integral = Integral(f, false)
        return integral.trapezoidal(lowerLimit, upperLimit)
    }

    @Throws(CalculatorException::class)
    fun simpson(f: String, lowerLimit: Double, upperLimit: Double): Double? {
        val integral = Integral(f, false)
        return integral.simpson(lowerLimit, upperLimit)
    }

    @Throws(CalculatorException::class)
    fun romberg(f: String, lowerLimit: Double, upperLimit: Double, steps: Int): Double? {
        val integral = Integral(f, false)
        return integral.Romberg(lowerLimit, upperLimit, steps)
    }

}
