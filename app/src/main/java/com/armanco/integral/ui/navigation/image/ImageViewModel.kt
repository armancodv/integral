package com.armanco.integral.ui.navigation.image

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.armanco.integral.data.repository.StatRepository
import com.armanco.integral.data.repository.StatRepository.Companion.XP_AD
import com.armanco.integral.utils.extensions.configAds
import com.armanco.integral.utils.facade.EventFacade
import com.google.firebase.remoteconfig.FirebaseRemoteConfig
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ImageViewModel @Inject constructor(
    private val eventFacade: EventFacade,
    private val remoteConfig: FirebaseRemoteConfig,
    private val statRepository: StatRepository
) : ViewModel() {
    var configAds = MutableLiveData(remoteConfig.configAds)
    fun load() {
        eventFacade.screenView(ImageFragment::class.java.simpleName)
    }

    fun adShown() {
        viewModelScope.launch {
            statRepository.increaseXp(XP_AD)
            statRepository.increaseCounterAd()
        }
    }
}