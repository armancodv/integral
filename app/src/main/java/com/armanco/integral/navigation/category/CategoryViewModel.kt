package com.armanco.integral.navigation.category

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.armanco.integral.R
import com.armanco.integral.managers.repository.CategoryRepository
import com.armanco.integral.models.Category
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CategoryViewModel @Inject constructor(
    private val repository: CategoryRepository
): ViewModel() {
    val categories = MutableLiveData<List<Category>>()

    fun load() {
        viewModelScope.launch {
            categories.value = repository.getAll()
        }
    }
}