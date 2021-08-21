package com.example.app.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.app.service.model.IngredientModel
import com.example.app.service.repository.IngredientRepository

/** GuestFormViewModel - no curso
 * Aulas: 149-158-163-164
 * ViewModel e onde fica as regras de negócio da aplicação
 * class DialogAddIngredientViewModel(application: Application): AndroidViewModel(application)
 * application: Application - pega o contexto que foi chamado a classe DialogAddIngredientViewModel */
class CreateAndUpdateIngredientViewModel(application: Application): AndroidViewModel(application) {

    private val mContext = application.applicationContext
    private val mIngredientsRepository: IngredientRepository = IngredientRepository.getInstance(mContext)

    private var mSaveIngredient= MutableLiveData<Boolean>()
    val saveIngredient: LiveData<Boolean> = mSaveIngredient

    private var mIngredient = MutableLiveData<IngredientModel>()
    val ingredient: LiveData<IngredientModel> = mIngredient

    fun save (id: Int, name: String, brand: String, price: String,
              quantity: String, unitMeasure: String, photo: String ) {
        val ingredient = IngredientModel(id, name, brand, price, quantity,unitMeasure, photo)
        if(id == 0){
            mSaveIngredient.value = mIngredientsRepository.save(ingredient)
        }else {
            mSaveIngredient.value = mIngredientsRepository.update(ingredient)
        }
    }

    fun load(id: Int) {
        mIngredient.value = mIngredientsRepository.get(id)
    }
}