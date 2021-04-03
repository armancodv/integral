package com.armanco.integral.navigation.list

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.armanco.integral.models.Category

class ListViewModel: ViewModel() {
    val categories = MutableLiveData<List<Category>>()

    fun load() {
    }
}