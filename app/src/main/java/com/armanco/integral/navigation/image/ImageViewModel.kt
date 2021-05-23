package com.armanco.integral.navigation.image

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.armanco.integral.utils.extensions.configAds
import com.armanco.integral.utils.facade.EventFacade
import com.google.firebase.remoteconfig.FirebaseRemoteConfig
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ImageViewModel @Inject constructor(
    private val eventFacade: EventFacade,
    private val remoteConfig: FirebaseRemoteConfig,
) : ViewModel() {
    var configAds = MutableLiveData(remoteConfig.configAds)
    fun load() {
        eventFacade.screenView(ImageFragment::class.java.simpleName)
    }
}