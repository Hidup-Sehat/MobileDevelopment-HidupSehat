package com.bangkit23.hidupsehat.presentation.screen.food_information_detail

import com.bangkit23.hidupsehat.domain.model.food.Food

data class DetailFoodInformationState(
    val notEditedFood: Food? = null,
    val food: Food? = null,
    val portionSize : List<Food> = emptyList(),
    val count: Int = 1,
)
