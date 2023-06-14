package com.bangkit23.hidupsehat.domain.model.food

data class AddFoods(
    val date: String? = null,
    val lastUpdated: String? = null,
    val totalCarb: Int? = null,
    val totalProtein: Int? = null,
    val totalFat: Int? = null,
    val id: String? = null,
    val totalFiber: Int? = null,
    val foods: List<FoodItem>
)

data class FoodItem(
    val portionSize: String? = null,
    val id: Int? = null,
    val foodName: String? = null,
    val calorie: Int? = null,
)
