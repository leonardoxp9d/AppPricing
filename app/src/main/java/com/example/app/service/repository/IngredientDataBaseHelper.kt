package com.example.app.service.repository

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.example.app.service.constants.DataBaseConstants

// Classe de conexão com o banco
// GuestDataBaseHelper - usaremos essa classe para acessar o banco - e o coração do banco
// classe sqliteOpenHelper faz conexão com o banco
class IngredientDataBaseHelper(context: Context): SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {
    // Quando chamar o banco de dados, ja cai nessa função de onCreate e cria a tabela
    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL(CREATE_TABLE_INGREDIENT)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {

    }

    companion object {
        private const val DATABASE_VERSION = 1
        private const val DATABASE_NAME = "AppPrincing.db"
        // script para criar tabela no banco de dados
        private const val CREATE_TABLE_INGREDIENT =
            ("create table " + DataBaseConstants.INGREDIENT.TABLE_NAME + " ("
                    + DataBaseConstants.INGREDIENT.COLUMNS.ID + " integer primary key autoincrement, "
                    + DataBaseConstants.INGREDIENT.COLUMNS.NAME + " text, "
                    + DataBaseConstants.INGREDIENT.COLUMNS.BRAND + " text, "
                    + DataBaseConstants.INGREDIENT.COLUMNS.PRICE + " text, "
                    + DataBaseConstants.INGREDIENT.COLUMNS.QUANTITY + " text, " //integer
                    + DataBaseConstants.INGREDIENT.COLUMNS.UNITMEASURE + " text, "
                    + DataBaseConstants.INGREDIENT.COLUMNS.PHOTO + " text);")
    }
}