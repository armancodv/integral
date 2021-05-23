package com.armanco.integral.navigation.settings

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.armanco.integral.utils.extensions.configLinks
import com.armanco.integral.utils.facade.EventFacade
import com.google.firebase.remoteconfig.FirebaseRemoteConfig
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SettingsViewModel @Inject constructor(
    private val eventFacade: EventFacade,
    private val remoteConfig: FirebaseRemoteConfig,
): ViewModel() {

    var configLinks = MutableLiveData(remoteConfig.configLinks)

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

    fun selectPrivacy() {
        eventFacade.selectPrivacy()
    }

    fun selectTerms() {
        eventFacade.selectTerms()
    }

    fun selectRate() {
       eventFacade.selectRate()
    }

    fun submitRate() {
        eventFacade.submitRate()
    }
}