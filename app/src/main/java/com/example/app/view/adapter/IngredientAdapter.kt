package com.example.app.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.app.R
import com.example.app.service.model.IngredientModel
import com.example.app.view.listener.IngredientListener
import com.example.app.view.viewholder.IngredientViewHolder

/** GuestAdapter - referente a essa classe no curso
 * Aulas: 160-161-163-
 Classe para fazer o Recycler view dos Ingredientes
 adapter também e responsavel por criar o layout, como se fosse o "inflate()"*/
class IngredientAdapter : RecyclerView.Adapter<IngredientViewHolder>() {

    private var mIngredientList: List<IngredientModel> = arrayListOf()

    private lateinit var mListener: IngredientListener

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): IngredientViewHolder {
        val item = LayoutInflater.from(parent.context).inflate(R.layout.row_ingredient, parent,false)
        return IngredientViewHolder(item, mListener)
    }

    override fun onBindViewHolder(holder: IngredientViewHolder, position: Int) {
        holder.bind(mIngredientList[position])
    }

    override fun getItemCount(): Int {
        return mIngredientList.count()
    }

    fun updateIngredients(list: List<IngredientModel>) {
        mIngredientList = list
        notifyDataSetChanged()
    }

    fun attachListener(listener: IngredientListener) {
        mListener = listener
    }

}