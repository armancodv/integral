package com.armanco.integral.navigation.items

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.armanco.integral.managers.repository.CategoryRepository
import com.armanco.integral.managers.repository.ItemRepository
import com.armanco.integral.models.Item
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ItemsViewModel @Inject constructor(
    private val repository: ItemRepository
): ViewModel() {
    val items = MutableLiveData<List<Item>>()

    fun load(id: String?) {
        viewModelScope.launch {
            items.value = id?.let { repository.getByCategory(it) }
        }
    }
}