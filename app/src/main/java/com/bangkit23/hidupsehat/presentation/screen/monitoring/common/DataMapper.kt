package com.bangkit23.hidupsehat.presentation.screen.monitoring.common

import com.bangkit23.hidupsehat.domain.model.food.Food
import com.bangkit23.hidupsehat.domain.model.food.FoodHistoryDetailItem
import com.bangkit23.hidupsehat.domain.model.food.FoodsHistoryItem
import com.bangkit23.hidupsehat.presentation.screen.monitoring.model.Nutrition

fun FoodsHistoryItem.toFood() = Food(
    id = id ?: 0,
    name = foodName,
    portionSize = portionSize,
    energyKKal = calorie?.toDouble() ?: 0.0
)

fun List<FoodHistoryDetailItem>.toNutritionProtein() = map {
    Nutrition(
        id = it.id?.toIntOrNull() ?: 0,
        nutritionName = "Protein",
        total = it.totalProtein ?: 0,
        target = 55,
        remain = 55 - (it.totalProtein ?: 0),
    )
}

fun List<FoodHistoryDetailItem>.toNutritionCarbohydrate() = map {
    Nutrition(
        id = it.id?.toIntOrNull() ?: 0,
        nutritionName = "Karbohidrat",
        total = it.totalCarb ?: 0,
        target = 200,
        remain = 200 - (it.totalCarb ?: 0),
    )
}