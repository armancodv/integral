package com.armanco.integral

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.armanco.integral.utils.extensions.configAds
import com.armanco.integral.utils.facade.EventFacade
import com.google.firebase.remoteconfig.FirebaseRemoteConfig
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val eventFacade: EventFacade,
    val remoteConfig: FirebaseRemoteConfig,
): ViewModel() {
    var configAds = MutableLiveData(remoteConfig.configAds)

    fun initEvents(context: Context) {
        eventFacade.init(context)
    }
}