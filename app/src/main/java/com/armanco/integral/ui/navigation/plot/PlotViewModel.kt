package com.armanco.integral.ui.navigation.plot

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.armanco.integral.data.repository.PlotRepository
import com.armanco.integral.data.models.PlotEntries
import com.armanco.integral.data.repository.StatRepository
import com.armanco.integral.utils.extensions.configAds
import com.armanco.integral.utils.facade.EventFacade
import com.google.firebase.remoteconfig.FirebaseRemoteConfig
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PlotViewModel @Inject constructor(
    private val repository: PlotRepository,
    private val statRepository: StatRepository,
    private val eventFacade: EventFacade,
    private val remoteConfig: FirebaseRemoteConfig,
): ViewModel() {
    val configAds = MutableLiveData(remoteConfig.configAds)
    val function = MutableLiveData("sin(x)")
    val lowerLimit = MutableLiveData(0.0)
    val upperLimit = MutableLiveData(10.0)
    val steps = MutableLiveData(100)
    val plotEntries = MutableLiveData<PlotEntries>()
    val lastFunction = MutableLiveData(function.value)

    fun load() {
        eventFacade.screenView(PlotFragment::class.java.simpleName)
    }

    fun plot(track: Boolean = true) {
        if(function.value != null && lowerLimit.value!=null && upperLimit.value!=null && steps.value!=null) {
            plotEntries.postValue(
                PlotEntries(
                    entries = repository.entries(function.value!!, lowerLimit.value!!, upperLimit.value!!, steps.value!!),
                    entriesIntegral = repository.entriesIntegral(function.value!!, lowerLimit.value!!, upperLimit.value!!, steps.value!!),
                )
            )
            if(track) {
                viewModelScope.launch {
                    if(lastFunction.value != function.value) {
                        lastFunction.postValue(function.value)
                        statRepository.increaseXp(StatRepository.XP_PLOT)
                        val counter = statRepository.increaseCounterPlot()
                        eventFacade.plot(
                            function = function.value,
                            lowerLimit = lowerLimit.value,
                            upperLimit = upperLimit.value,
                            steps = steps.value,
                            counter = counter
                        )
                    } else {
                        eventFacade.plot(
                            function = function.value,
                            lowerLimit = lowerLimit.value,
                            upperLimit = upperLimit.value,
                            steps = steps.value
                        )
                    }
                }
            }
        }
    }

    fun adShown() {
        viewModelScope.launch {
            statRepository.increaseXp(StatRepository.XP_AD)
            statRepository.increaseCounterAd()
        }
    }

}