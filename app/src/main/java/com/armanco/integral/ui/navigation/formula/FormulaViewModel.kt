package com.armanco.integral.ui.navigation.formula

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.armanco.integral.data.repository.FormulaRepository
import com.armanco.integral.data.models.Formula
import com.armanco.integral.data.repository.StatRepository
import com.armanco.integral.utils.facade.EventFacade
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FormulaViewModel @Inject constructor(
    private val repository: FormulaRepository,
    private val statRepository: StatRepository,
    private val eventFacade: EventFacade
): ViewModel() {
    val formulas = MutableLiveData<List<Formula>>()
    fun load(id: Int?) {
        eventFacade.screenView(FormulaFragment::class.java.simpleName)
        viewModelScope.launch {
            formulas.value = id?.let { repository.getByCategory(it) }
        }
    }

    fun selectImage(id: Int, categoryId: Int, title: String) {
        viewModelScope.launch {
            statRepository.increaseXp(StatRepository.XP_FORMULA)
            val counter = statRepository.increaseCounterFormula()
            eventFacade.selectImage(id, categoryId, title, counter)
        }
    }
}