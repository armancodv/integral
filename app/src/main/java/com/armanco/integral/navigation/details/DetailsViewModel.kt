package com.armanco.integral.navigation.details

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.armanco.integral.models.Item

class DetailsViewModel: ViewModel() {
    val items = MutableLiveData<List<Item>>()

    fun load(id: String?) {
    }
}