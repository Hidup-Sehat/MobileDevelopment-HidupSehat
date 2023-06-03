package com.bangkit23.hidupsehat.domain.model.food

data class Food(
    val id: Int,
    val name: String? = null,
    val portionSize: String? = null,
    val energyKj: Double? = null,
    var energyKKal: Double? = null,
    val sugar: Double? = null,
    val potassium: Double? = null,
    val carbohydrate: Double? = null,
    val cholesterol: Double? = null,
    val fat: Double? = null,
    val saturatedFat: Double? = null,
    val transFat: Double? = null,
    val polyunsaturatedFat: Double? = null,
    val monounsaturatedFat: Double? = null,
    val protein: Double? = null,
    val fiber: Double? = null,
    val sodium: Double? = null,
    var count: Int = 1,
)
