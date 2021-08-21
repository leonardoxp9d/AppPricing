package com.example.app.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MaterialsViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "MATERIAIS"
    }
    val text: LiveData<String> = _text
}