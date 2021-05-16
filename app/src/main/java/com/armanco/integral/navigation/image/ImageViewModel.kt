package com.armanco.integral.navigation.image

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.armanco.integral.managers.repository.FormulaRepository
import com.armanco.integral.models.Formula
import com.armanco.integral.utils.facade.EventFacade
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ImageViewModel @Inject constructor(
    private val eventFacade: EventFacade
): ViewModel() {
    fun load() {
        eventFacade.screenView(ImageFragment::class.java.simpleName)
    }
}