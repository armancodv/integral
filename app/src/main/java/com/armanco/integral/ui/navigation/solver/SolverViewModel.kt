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

    fun load() {
        eventFacade.screenView(SolverFragment::class.java.simpleName)
    }
}