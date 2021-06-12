package com.armanco.integral.ui.navigation.solver

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.armanco.integral.data.repository.SolverRepository
import com.armanco.integral.data.repository.StatRepository
import com.armanco.integral.utils.extensions.configAds
import com.armanco.integral.utils.facade.EventFacade
import com.google.firebase.remoteconfig.FirebaseRemoteConfig
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SolverViewModel @Inject constructor(
    private val repository: SolverRepository,
    private val statRepository: StatRepository,
    private val eventFacade: EventFacade,
    private val remoteConfig: FirebaseRemoteConfig,
): ViewModel() {
    val configAds = MutableLiveData(remoteConfig.configAds)
    val result = MutableLiveData<Double?>()
    val method = MutableLiveData(Method.Romberg)
    val hasSteps = MutableLiveData(true)
    val function = MutableLiveData("sin(x)")
    val steps = MutableLiveData(1)
    val lowerLimit = MutableLiveData(0.0)
    val upperLimit = MutableLiveData(1.0)
    val lastFunction = MutableLiveData(function.value)

    fun load() {
        eventFacade.screenView(SolverFragment::class.java.simpleName)
    }

    fun setMethod(method: Method) {
        this.method.value = method
        this.hasSteps.value = (method == Method.Romberg)
    }

    fun calculate() {
        when(method.value) {
            Method.Trapezoidal -> result.postValue(repository.trapezoidal(function.value ?: "", lowerLimit.value ?: 0.0, upperLimit.value ?: 0.0))
            Method.Simpson -> result.postValue(repository.simpson(function.value ?: "", lowerLimit.value ?: 0.0, upperLimit.value ?: 0.0))
            Method.Romberg -> result.postValue(repository.romberg(function.value ?: "", lowerLimit.value ?: 0.0, upperLimit.value ?: 0.0, steps.value ?: 1))
        }
        viewModelScope.launch {
            if(lastFunction.value != function.value) {
                lastFunction.postValue(function.value)
                statRepository.increaseXp(StatRepository.XP_CALCULATE)
                val counter = statRepository.increaseCounterCalculate()
                eventFacade.calculate(method = method.value.toString(), function = function.value, lowerLimit = lowerLimit.value, upperLimit = upperLimit.value, steps = steps.value, result = result.value, counter = counter)
            } else {
                eventFacade.calculate(method = method.value.toString(), function = function.value, lowerLimit = lowerLimit.value, upperLimit = upperLimit.value, steps = steps.value, result = result.value)
            }
        }
    }

    fun adShown() {
        viewModelScope.launch {
            statRepository.increaseXp(StatRepository.XP_AD)
            statRepository.increaseCounterAd()
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