package com.armanco.integral.navigation.solver

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.armanco.integral.managers.repository.SolverRepository
import com.armanco.integral.utils.facade.EventFacade
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SolverViewModel @Inject constructor(
    private val repository: SolverRepository,
    private val eventFacade: EventFacade
): ViewModel() {
    val result = MutableLiveData<Double?>()
    val method = MutableLiveData(Method.Simpson)
    val hasSteps = MutableLiveData(false)
    val function = MutableLiveData("2*x^2+sin(x)+1")
    val steps = MutableLiveData(1)
    val lowerLimit = MutableLiveData(0.0)
    val upperLimit = MutableLiveData(1.0)

    fun load() {
        eventFacade.screenView(SolverFragment::class.java.simpleName)
    }

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
        eventFacade.calculate(method = method.value.toString(), function = function.value, lowerLimit = lowerLimit.value, upperLimit = upperLimit.value, steps = steps.value, result = result.value)
    }

    companion object {
        enum class Method {
            Trapezoidal,
            Simpson,
            Romberg
        }
    }
}