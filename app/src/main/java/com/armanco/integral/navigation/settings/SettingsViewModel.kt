package com.armanco.integral.navigation.settings

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.armanco.integral.managers.repository.FormulaRepository
import com.armanco.integral.models.Formula
import com.armanco.integral.utils.facade.EventFacade
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SettingsViewModel @Inject constructor(
    private val eventFacade: EventFacade
): ViewModel() {

    fun load() {
        eventFacade.screenView(SettingsFragment::class.java.simpleName)
    }

    fun selectProVersion() {
        eventFacade.selectProVersion()
    }

    fun selectReportBug() {
        eventFacade.selectReportBug()
    }

    fun selectContribute() {
        eventFacade.selectContribute()
    }

    fun selectRate() {
       eventFacade.selectRate()
    }

    fun submitRate() {
        eventFacade.submitRate()
    }
}