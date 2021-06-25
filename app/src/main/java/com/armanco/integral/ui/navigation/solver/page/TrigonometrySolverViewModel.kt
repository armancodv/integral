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
import kotlin.math.PI
import kotlin.math.cos
import kotlin.math.sin
import kotlin.math.tan

@HiltViewModel
class TrigonometrySolverViewModel @Inject constructor(
    private val repository: SolverRepository,
    private val statRepository: StatRepository,
    private val eventFacade: EventFacade,
    private val remoteConfig: FirebaseRemoteConfig,
): ViewModel() {
    val configAds = MutableLiveData(remoteConfig.configAds)
    val angle = MutableLiveData(0.0)
    val sin = MutableLiveData(0.0)
    val cos = MutableLiveData(0.0)
    val tan = MutableLiveData(0.0)
    val cot = MutableLiveData(0.0)
    val csc = MutableLiveData(0.0)
    val sec = MutableLiveData(0.0)
    val unit = MutableLiveData(AngleUnit.DEGREE)

    fun load() {
        eventFacade.screenView(TrigonometrySolverViewPager::class.java.simpleName)
    }

    private fun calculate(angle: Double?, unit: AngleUnit?) {
        val radianAngle = when(unit) {
            AngleUnit.RADIAN -> angle ?: 0.0
            else -> (angle ?: 0.0) * PI / 180.0
        }
        val sinVal = sin(radianAngle)
        val cosVal = cos(radianAngle)
        val tanVal = tan(radianAngle)
        sin.postValue(sinVal.round(4))
        cos.postValue(cosVal.round(4))
        tan.postValue(tanVal.round(4))
        cot.postValue((1 / tanVal).round(4))
        csc.postValue((1 / sinVal).round(4))
        sec.postValue((1 / cosVal).round(4))
    }

    fun adShown() {
        viewModelScope.launch {
            statRepository.increaseXp(StatRepository.XP_AD)
            statRepository.increaseCounterAd()
        }
    }

    fun changeUnit(unit: AngleUnit) {
        this.unit.postValue(unit)
        calculate(angle.value, unit)
    }

    fun changeAngle(angle: Double?) {
        this.angle.postValue(angle)
        calculate(angle, unit.value)
    }
}