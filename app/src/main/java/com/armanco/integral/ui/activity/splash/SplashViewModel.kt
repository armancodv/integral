package com.armanco.integral.ui.activity.splash

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.armanco.integral.data.models.User
import com.armanco.integral.data.repository.CategoryRepository
import com.armanco.integral.data.repository.FormulaRepository
import com.armanco.integral.data.repository.StatRepository
import com.armanco.integral.data.repository.UserRepository
import com.armanco.integral.utils.facade.AuthFacade
import com.armanco.integral.utils.facade.InitFacade
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.ktx.toObject
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import javax.inject.Inject
import kotlin.math.max

@HiltViewModel
class SplashViewModel @Inject constructor(
    private val categoryRepository: CategoryRepository,
    private val formulaRepository: FormulaRepository,
    private val userRepository: UserRepository,
    private val statRepository: StatRepository,
    private val initFacade: InitFacade,
): ViewModel() {
    val isReady = MutableLiveData(false)

    fun load() {
        viewModelScope.launch {
            populate()
            initFacade.init()
            isReady.postValue(true)
        }
    }

    private suspend fun populate() {
        categoryRepository.populate()
        formulaRepository.populate()
    }



}