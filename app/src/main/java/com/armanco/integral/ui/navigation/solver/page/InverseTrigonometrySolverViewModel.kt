package com.armanco.integral.ui.navigation.solver.page

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.armanco.integral.data.models.AngleUnit
import com.armanco.integral.data.repository.SolverRepository
import com.armanco.integral.data.repository.StatRepository
import com.armanco.integral.utils.extensions.configAds
import com.armanco.integral.utils.extensions.round
import com.armanco.integral.utils.facade.EventFacade
import com.google.firebase.remoteconfig.FirebaseRemoteConfig
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.math.*
import kotlin.math.cos
import kotlin.math.sin

@HiltViewModel
class InverseTrigonometrySolverViewModel @Inject constructor(
    private val repository: SolverRepository,
    private val statRepository: StatRepository,
    private val eventFacade: EventFacade,
    private val remoteConfig: FirebaseRemoteConfig,
): ViewModel() {
    val configAds = MutableLiveData(remoteConfig.configAds)
    val arc = MutableLiveData(0.0)
    val asin = MutableLiveData(0.0)
    val acos = MutableLiveData(0.0)
    val atan = MutableLiveData(0.0)
    val acot = MutableLiveData(0.0)
    val acsc = MutableLiveData(0.0)
    val asec = MutableLiveData(0.0)
    val unit = MutableLiveData(AngleUnit.DEGREE)

    fun load() {
        eventFacade.screenView(TrigonometrySolverViewPager::class.java.simpleName)
    }

    private fun calculate(arc: Double?, unit: AngleUnit?) {
        val multiplier = when(unit) {
            AngleUnit.RADIAN -> 1.0
            else -> 180.0 / PI
        }
        asin.postValue((asin(arc ?: 0.0)*multiplier).round(4))
        acos.postValue((acos(arc ?: 0.0)*multiplier).round(4))
        atan.postValue((atan(arc ?: 0.0)*multiplier).round(4))
        acot.postValue(((PI / 2 - atan(arc ?: 0.0))*multiplier).round(4))
        acsc.postValue((asin(1 / (arc ?: 0.0))*multiplier).round(4))
        asec.postValue((acos(1 / (arc ?: 0.0))*multiplier).round(4))
    }

    fun adShown() {
        viewModelScope.launch {
            statRepository.increaseXp(StatRepository.XP_AD)
            statRepository.increaseCounterAd()
        }
    }

    fun changeUnit(unit: AngleUnit) {
        this.unit.postValue(unit)
        calculate(arc.value, unit)
    }

    fun changeArc(arc: Double?) {
        this.arc.postValue(arc)
        calculate(arc, unit.value)
    }
}