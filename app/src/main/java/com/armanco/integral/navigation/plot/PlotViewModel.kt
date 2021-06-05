package com.armanco.integral.navigation.plot

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.armanco.integral.managers.repository.PlotRepository
import com.armanco.integral.models.PlotEntries
import com.armanco.integral.utils.facade.EventFacade
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class PlotViewModel @Inject constructor(
    private val repository: PlotRepository,
    private val eventFacade: EventFacade
): ViewModel() {
    val function = MutableLiveData("sin(x)")
    val lowerLimit = MutableLiveData(0.0)
    val upperLimit = MutableLiveData(10.0)
    val steps = MutableLiveData(100)
    val plotEntries = MutableLiveData<PlotEntries>()

    fun load() {
        eventFacade.screenView(PlotFragment::class.java.simpleName)
    }

    fun plot() {
        if(function.value != null && lowerLimit.value!=null && upperLimit.value!=null && steps.value!=null) {
            plotEntries.postValue(
                PlotEntries(
                    entries = repository.entries(function.value!!, lowerLimit.value!!, upperLimit.value!!, steps.value!!),
                    entriesIntegral = repository.entriesIntegral(function.value!!, lowerLimit.value!!, upperLimit.value!!, steps.value!!),
                )
            )
            eventFacade.plot(function = function.value, lowerLimit = lowerLimit.value, upperLimit = upperLimit.value, steps = steps.value)
        }
    }

}