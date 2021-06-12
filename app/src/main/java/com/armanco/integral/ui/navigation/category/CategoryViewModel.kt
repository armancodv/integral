package com.armanco.integral.ui.navigation.category

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.armanco.integral.data.repository.CategoryRepository
import com.armanco.integral.data.repository.FormulaRepository
import com.armanco.integral.data.models.Category
import com.armanco.integral.utils.facade.EventFacade
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CategoryViewModel @Inject constructor(
    private val repository: CategoryRepository,
    private val formulaRepository: FormulaRepository,
    private val eventFacade: EventFacade
): ViewModel() {
    val categories = MutableLiveData<List<Category>>()

    fun load() {
        eventFacade.screenView(CategoryFragment::class.java.simpleName)
        viewModelScope.launch {
            categories.value = repository.getAll()
        }
    }

    fun selectCategory(id: Int, title: String) {
        eventFacade.selectCategory(id, title)
    }
}