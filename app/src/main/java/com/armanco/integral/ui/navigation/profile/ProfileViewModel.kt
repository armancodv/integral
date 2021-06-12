package com.armanco.integral.ui.navigation.profile

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.armanco.integral.data.repository.StatRepository
import com.armanco.integral.utils.XpLevel
import com.armanco.integral.utils.extensions.configLinks
import com.armanco.integral.utils.facade.AuthFacade
import com.armanco.integral.utils.facade.EventFacade
import com.armanco.integral.utils.facade.InitFacade
import com.google.firebase.remoteconfig.FirebaseRemoteConfig
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val eventFacade: EventFacade,
    private val remoteConfig: FirebaseRemoteConfig,
    private val statRepository: StatRepository,
    private val initFacade: InitFacade,
): ViewModel() {

    val isLoggedIn = MutableLiveData(false)
    val firebaseUser = MutableLiveData(AuthFacade.user)
    val xp = MutableLiveData(0)
    val level = MutableLiveData(1)
    val progress = MutableLiveData(0)
    val counterPlot = MutableLiveData(0)
    val counterCalculate = MutableLiveData(0)
    val counterFormula = MutableLiveData(0)
    val counterAd = MutableLiveData(0)
    var configLinks = MutableLiveData(remoteConfig.configLinks)

    fun load() {
        isLoggedIn.postValue(AuthFacade.isLoggedIn)
        viewModelScope.launch {
            update()
        }
    }

    suspend fun update() {
        val xpValue = statRepository.xp.first() ?: 0
        xp.postValue(xpValue)
        val currentLevel = XpLevel.xpToLevel(xpValue)
        val minXp = XpLevel.levelToMinXp(currentLevel)
        val maxXp = XpLevel.levelToMaxXp(currentLevel)
        val currentProgress = (xpValue - minXp) * 100 / (maxXp - minXp)
        level.postValue(currentLevel)
        progress.postValue(currentProgress)
        counterPlot.postValue(statRepository.counterPlot.first() ?: 0)
        counterCalculate.postValue(statRepository.counterCalculate.first() ?: 0)
        counterFormula.postValue(statRepository.counterFormula.first() ?: 0)
        counterAd.postValue(statRepository.counterAd.first() ?: 0)
    }

    fun selectLogin() {
        eventFacade.selectLogin()
    }

    fun submitLogin() {
        eventFacade.selectLogin()
        isLoggedIn.postValue(AuthFacade.isLoggedIn)
        firebaseUser.postValue(AuthFacade.user)
        viewModelScope.launch {
            initFacade.init()
            update()
        }
    }

}