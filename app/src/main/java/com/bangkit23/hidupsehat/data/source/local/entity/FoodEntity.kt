package com.bangkit23.hidupsehat.data.source.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "food_entity")
data class FoodEntity(
    @PrimaryKey
    val id: Int = 0,
    val name: String? = null,
    val portionSize: String? = null,
    val energyKj: Double? = null,
    val energyKKal: Double? = null,
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
)
