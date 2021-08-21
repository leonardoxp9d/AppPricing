package com.example.app.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class CalculationsViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "CÁLCULOS"
    }
    val text: LiveData<String> = _text
}