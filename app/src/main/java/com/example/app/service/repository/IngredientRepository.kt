package com.example.app.service.repository

import android.content.ContentValues
import android.content.Context
import com.example.app.service.constants.DataBaseConstants
import com.example.app.service.model.IngredientModel
import java.lang.Exception

/** Classe GuestRepository - curso
 * Na pasta repository ficam as Classes que tem
 * como responsabilidade de salvar os valores no banco de dados
 * Aulas: 149-154-152-155-156-157*/
class IngredientRepository private constructor(context: Context) {

    private var mIngredientDataBaseHelper: IngredientDataBaseHelper = IngredientDataBaseHelper(context)

    companion object {
        private lateinit var repository: IngredientRepository

        fun getInstance(context: Context) : IngredientRepository {
            if (!::repository.isInitialized){
                repository = IngredientRepository(context)
            }
            return IngredientRepository(context)
        }
    }

    fun get(id: Int) : IngredientModel? {
        var ingredient: IngredientModel? = null
        return try{
            val db = mIngredientDataBaseHelper.readableDatabase

            val projection = arrayOf(
                DataBaseConstants.INGREDIENT.COLUMNS.NAME,
                DataBaseConstants.INGREDIENT.COLUMNS.BRAND,
                DataBaseConstants.INGREDIENT.COLUMNS.PRICE,
                DataBaseConstants.INGREDIENT.COLUMNS.QUANTITY,
                DataBaseConstants.INGREDIENT.COLUMNS.UNITMEASURE,
                DataBaseConstants.INGREDIENT.COLUMNS.PHOTO,
                )

            val selection = DataBaseConstants.INGREDIENT.COLUMNS.ID + " = ?"
            val args = arrayOf(id.toString())

            val cursor = db.query(
                DataBaseConstants.INGREDIENT.TABLE_NAME,
                projection,
                selection,
                args,
                null,
                null,
                null
            )

            if (cursor != null && cursor.count > 0){
                cursor.moveToFirst()
                val name = cursor.getString(cursor.getColumnIndex(DataBaseConstants.INGREDIENT.COLUMNS.NAME))
                val brand = cursor.getString(cursor.getColumnIndex(DataBaseConstants.INGREDIENT.COLUMNS.BRAND))
                val price = cursor.getString(cursor.getColumnIndex(DataBaseConstants.INGREDIENT.COLUMNS.PRICE))
                val quantity = cursor.getString(cursor.getColumnIndex(DataBaseConstants.INGREDIENT.COLUMNS.QUANTITY)) //getInt
                val unitMeasure = cursor.getString(cursor.getColumnIndex(DataBaseConstants.INGREDIENT.COLUMNS.UNITMEASURE))
                val photo = cursor.getString(cursor.getColumnIndex(DataBaseConstants.INGREDIENT.COLUMNS.PHOTO))

                ingredient = IngredientModel(id, name, brand, price, quantity, unitMeasure, photo)
            }

            cursor?.close()
            ingredient
        }catch (e: Exception) {
            ingredient
        }
    }

    fun getAll(): List<IngredientModel> {
        val list: MutableList<IngredientModel> = ArrayList()
        return try{
            val db = mIngredientDataBaseHelper.readableDatabase

            val projection = arrayOf(
                DataBaseConstants.INGREDIENT.COLUMNS.ID,
                DataBaseConstants.INGREDIENT.COLUMNS.NAME,
                DataBaseConstants.INGREDIENT.COLUMNS.BRAND,
                DataBaseConstants.INGREDIENT.COLUMNS.PRICE,
                DataBaseConstants.INGREDIENT.COLUMNS.QUANTITY,
                DataBaseConstants.INGREDIENT.COLUMNS.UNITMEASURE,
                DataBaseConstants.INGREDIENT.COLUMNS.PHOTO,
            )

            val cursor = db.query(
                DataBaseConstants.INGREDIENT.TABLE_NAME,
                projection,
                null,
                null,
                null,
                null,
                null
            )

            if (cursor != null && cursor.count > 0){

                while (cursor.moveToNext()) {
                    val id = cursor.getInt(cursor.getColumnIndex(DataBaseConstants.INGREDIENT.COLUMNS.ID))
                    val name = cursor.getString(cursor.getColumnIndex(DataBaseConstants.INGREDIENT.COLUMNS.NAME))
                    val brand = cursor.getString(cursor.getColumnIndex(DataBaseConstants.INGREDIENT.COLUMNS.BRAND))
                    val price = cursor.getString(cursor.getColumnIndex(DataBaseConstants.INGREDIENT.COLUMNS.PRICE))
                    val quantity = cursor.getString(cursor.getColumnIndex(DataBaseConstants.INGREDIENT.COLUMNS.QUANTITY)) //getInt
                    val unitMeasure = cursor.getString(cursor.getColumnIndex(DataBaseConstants.INGREDIENT.COLUMNS.UNITMEASURE)) //getInt
                    val photo = cursor.getString(cursor.getColumnIndex(DataBaseConstants.INGREDIENT.COLUMNS.PHOTO)) //getInt

                    val ingredient = IngredientModel(id, name, brand, price, quantity, unitMeasure, photo)
                    list.add(ingredient)
                }
            }

            cursor?.close()
            list
        }catch (e: Exception) {
            list
        }
    }

    fun save(ingredient: IngredientModel): Boolean {
        return try{
            val db = mIngredientDataBaseHelper.writableDatabase // pega o banco de dados

            val contentValues = ContentValues()
            contentValues.put(DataBaseConstants.INGREDIENT.COLUMNS.NAME, ingredient.name)
            contentValues.put(DataBaseConstants.INGREDIENT.COLUMNS.BRAND, ingredient.brand)
            contentValues.put(DataBaseConstants.INGREDIENT.COLUMNS.PRICE, ingredient.price)
            contentValues.put(DataBaseConstants.INGREDIENT.COLUMNS.QUANTITY, ingredient.quantity)
            contentValues.put(DataBaseConstants.INGREDIENT.COLUMNS.UNITMEASURE, ingredient.unitMeasure)
            contentValues.put(DataBaseConstants.INGREDIENT.COLUMNS.PHOTO, ingredient.photo)

            db.insert(DataBaseConstants.INGREDIENT.TABLE_NAME, null, contentValues)
            true
        }catch (e: Exception) {
            false
        }
    }

    fun update(ingredient: IngredientModel): Boolean {
        return try{
            val db = mIngredientDataBaseHelper.writableDatabase

            val contentValues = ContentValues()
            contentValues.put(DataBaseConstants.INGREDIENT.COLUMNS.NAME, ingredient.name)
            contentValues.put(DataBaseConstants.INGREDIENT.COLUMNS.BRAND, ingredient.brand)
            contentValues.put(DataBaseConstants.INGREDIENT.COLUMNS.PRICE, ingredient.price)
            contentValues.put(DataBaseConstants.INGREDIENT.COLUMNS.QUANTITY, ingredient.quantity)
            contentValues.put(DataBaseConstants.INGREDIENT.COLUMNS.UNITMEASURE, ingredient.unitMeasure)
            contentValues.put(DataBaseConstants.INGREDIENT.COLUMNS.PHOTO, ingredient.photo)

            val selection = DataBaseConstants.INGREDIENT.COLUMNS.ID + " = ?"
            val args = arrayOf(ingredient.id.toString())

            db.update(DataBaseConstants.INGREDIENT.TABLE_NAME, contentValues, selection, args)
            true
        }catch (e: Exception) {
            false
        }
    }

    fun delete(id: Int): Boolean {
        return try{
            val db = mIngredientDataBaseHelper.writableDatabase
            val selection = DataBaseConstants.INGREDIENT.COLUMNS.ID + " = ?"
            val args = arrayOf(id.toString())

            db.delete(DataBaseConstants.INGREDIENT.TABLE_NAME, selection, args)
            true
        }catch (e: Exception) {
            false
        }
    }


}