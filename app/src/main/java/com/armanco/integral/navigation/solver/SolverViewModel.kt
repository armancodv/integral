package com.armanco.integral.navigation.solver

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.armanco.integral.managers.repository.SolverRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SolverViewModel @Inject constructor(
    private val repository: SolverRepository
): ViewModel() {
    val result = MutableLiveData<Double?>()
    val method = MutableLiveData(Method.Simpson)
    val hasSteps = MutableLiveData(false)
    val function = MutableLiveData("2*x^2+sin(x)+1")
    val steps = MutableLiveData(1)
    val lowerLimit = MutableLiveData(0.0)
    val upperLimit = MutableLiveData(1.0)

    fun setMethod(method: Method) {
        this.method.value = method
        this.hasSteps.value = (method == Method.Romberg)
    }

    fun calculate() {
        when(method.value) {
            Method.Trapezoidal -> result.value = repository.trapezoidal(function.value ?: "", lowerLimit.value ?: 0.0, upperLimit.value ?: 0.0)
            Method.Simpson -> result.value = repository.simpson(function.value ?: "", lowerLimit.value ?: 0.0, upperLimit.value ?: 0.0)
            Method.Romberg -> result.value = repository.romberg(function.value ?: "", lowerLimit.value ?: 0.0, upperLimit.value ?: 0.0, steps.value ?: 1)
        }
    }

    companion object {
        enum class Method {
            Trapezoidal,
            Simpson,
            Romberg
        }
    }
}