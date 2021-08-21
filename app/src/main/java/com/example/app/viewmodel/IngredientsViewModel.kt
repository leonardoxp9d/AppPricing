package com.example.app.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.app.service.model.IngredientModel
import com.example.app.service.repository.IngredientRepository

/** AllGuestsViewModel - referente a essa classe no curso
 * Aulas: 161-165*/
class IngredientsViewModel(application: Application) : AndroidViewModel(application) {

    private val mIngredientRepository = IngredientRepository.getInstance(application.applicationContext)

    private val mIngredientList = MutableLiveData<List<IngredientModel>>()
    val ingredientList: LiveData<List<IngredientModel>> = mIngredientList

    fun load() {
        mIngredientList.value = mIngredientRepository.getAll()
    }

    fun delete(id: Int) {
        mIngredientRepository.delete(id)
    }
}