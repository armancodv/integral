package com.armanco.integral.ui.navigation.settings

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.armanco.integral.data.repository.UserRepository
import com.armanco.integral.utils.extensions.configLinks
import com.armanco.integral.utils.facade.AuthFacade
import com.armanco.integral.utils.facade.EventFacade
import com.armanco.integral.utils.facade.InitFacade
import com.google.firebase.remoteconfig.FirebaseRemoteConfig
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SettingsViewModel @Inject constructor(
    private val eventFacade: EventFacade,
    private val remoteConfig: FirebaseRemoteConfig,
    private val initFacade: InitFacade,
    private val userRepository: UserRepository
): ViewModel() {

    val isLoggedIn = MutableLiveData(false)
    var configLinks = MutableLiveData(remoteConfig.configLinks)

    fun load() {
        isLoggedIn.postValue(AuthFacade.isLoggedIn)
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

    fun selectDelete(context: Context) {
        AuthFacade.user?.uid?.let {
            userRepository.delete(it)
        }
        AuthFacade.deleteAccount(context)
        eventFacade.selectDelete()
        isLoggedIn.postValue(false)
    }

    fun selectLogout(context: Context) {
        AuthFacade.signOut(context)
        eventFacade.selectLogout()
        isLoggedIn.postValue(false)
    }

    fun selectLogin() {
        eventFacade.selectLogin()
    }

    fun submitLogin() {
        isLoggedIn.postValue(AuthFacade.isLoggedIn)
        eventFacade.selectLogin()
        viewModelScope.launch {
            initFacade.init()
        }
    }
}