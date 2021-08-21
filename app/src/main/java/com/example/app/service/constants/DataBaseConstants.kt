package com.example.app.service.constants

/**
 * Todas as constantes utilizadas no banco de dados
 * Tabelas, Colunas
 */
class DataBaseConstants private constructor() {

    /**
     * Tabelas disponíveis no banco de dados com suas colunas
     */
    object INGREDIENT {
        const val TABLE_NAME = "Ingredient"

        object COLUMNS {
            const val ID = "id"
            const val NAME = "name"
            const val BRAND = "brand"
            const val PRICE = "price"
            const val QUANTITY = "quantity"
            const val UNITMEASURE = "unitMeasure"
            const val PHOTO = "photo"

        }
    }
}