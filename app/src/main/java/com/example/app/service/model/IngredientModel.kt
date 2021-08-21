package com.example.app.service.model

// CLasse para trafegar(mapear) dados/elementos, e como um DTO(data transfer object) em Typescript
// Classe que mapeam atributos, e como uma classe Entity/Model no Typescript
data class IngredientModel(
    val id: Int = 0,
    var name: String,
    var brand: String,
    var price: String,
    var quantity: String,
    var unitMeasure: String,
    var photo: String)