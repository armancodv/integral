package com.armanco.integral.navigation.formula

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.armanco.integral.R
import com.armanco.integral.managers.repository.FormulaRepository
import com.armanco.integral.models.Formula
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FormulaViewModel @Inject constructor(
    private val repository: FormulaRepository
): ViewModel() {
    val formulas = MutableLiveData<List<Formula>>()

    fun load(id: String?) {
        viewModelScope.launch {
            formulas.value = id?.let { repository.getByCategory(it) }
        }
    }
}