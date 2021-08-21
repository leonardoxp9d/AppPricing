package com.example.app.view.viewholder

import android.app.AlertDialog
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Base64
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.example.app.R
import com.example.app.service.model.IngredientModel
import com.example.app.view.listener.IngredientListener
import de.hdodenhof.circleimageview.CircleImageView

/** Aulas: 160-161-163-165
 * ViewHolder - classe que guarda as referências dos elementos de layout(elementos de interface)
 * ela faz manipulção e atribuição dos elementos de interface*/
class IngredientViewHolder(itemView: View, private val listener: IngredientListener) : RecyclerView.ViewHolder(itemView) {

    //161.atribui os valores do banco de dados para os elementos do layout
    fun bind(ingredient: IngredientModel) {
        val layout = itemView.findViewById<ConstraintLayout>(R.id.layout)
        val textName = itemView.findViewById<TextView>(R.id.text_name)
        val textBrand = itemView.findViewById<TextView>(R.id.text_brand)
        val textQuantity = itemView.findViewById<TextView>(R.id.text_quantity)
        val textUnitMeasure= itemView.findViewById<TextView>(R.id.text_unitMeasure)
        val textPrice = itemView.findViewById<TextView>(R.id.text_price)
        val imageViewTrash = itemView.findViewById<ImageView>(R.id.imageViewTrash)
        val circleImageViewIngredient = itemView.findViewById<CircleImageView>(R.id.circleImageViewIngredient)

        textName.text = ingredient.name
        textBrand.text = ingredient.brand
        textQuantity.text = ingredient.quantity
        textUnitMeasure.text = ingredient.unitMeasure
        textPrice.text = ingredient.price

        if(!ingredient.photo.equals("")) {
            var photoInBytes: ByteArray
            photoInBytes = Base64.decode(ingredient.photo, Base64.DEFAULT)
            var photoDecodedInBitmap: Bitmap = BitmapFactory.decodeByteArray(photoInBytes, 0, photoInBytes.size)
            circleImageViewIngredient.setImageBitmap(photoDecodedInBitmap)
        }

        layout.setOnClickListener {
            listener.onClick(ingredient.id)
        }

        layout.setOnLongClickListener {
            AlertDialog.Builder(itemView.context)
                .setTitle(R.string.title_remove_ingredient)
                .setMessage(R.string.ask_remove_ingredient)
                .setPositiveButton(R.string.remove) { dialog, which ->
                    listener.onDelete(ingredient.id)
                }
                .setNeutralButton(R.string.cancelar, null)
                .show()
            true
        }

        imageViewTrash.setOnClickListener {
            AlertDialog.Builder(itemView.context)
                .setTitle(R.string.title_remove_ingredient)
                .setMessage(R.string.ask_remove_ingredient)
                .setPositiveButton(R.string.remove) { dialog, which ->
                    listener.onDelete(ingredient.id)
                }
                .setNeutralButton(R.string.cancelar, null)
                .show()
            true
        }
        true // retorno do setOnLonglickListener
    }
}