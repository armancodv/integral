package com.armanco.integral.managers.repository

import com.mathlibrary.integral.Integral
import javax.inject.Inject

class SolverRepository @Inject constructor() {

    fun trapezoidal(f: String, lowerLimit: Double, upperLimit: Double): Double? {
        return try {
            val integral = Integral(f)
            integral.trapezoidal(lowerLimit, upperLimit)
        } catch (e: Exception) {
            null
        }
    }

    fun simpson(f: String, lowerLimit: Double, upperLimit: Double): Double? {
        return try {
            val integral = Integral(f)
            integral.simpson(lowerLimit, upperLimit)
        } catch (e: Exception) {
            null
        }
    }

    fun romberg(f: String, lowerLimit: Double, upperLimit: Double, steps: Int): Double? {
        return try {
            val integral = Integral(f)
            integral.Romberg(lowerLimit, upperLimit, steps)
        } catch (e: Exception) {
            null
        }
    }

}
