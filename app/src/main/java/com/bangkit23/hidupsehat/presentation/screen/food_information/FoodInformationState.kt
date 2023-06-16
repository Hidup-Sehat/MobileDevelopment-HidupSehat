package com.bangkit23.hidupsehat.presentation.screen.food_information

import com.bangkit23.hidupsehat.domain.model.food.Food

data class FoodInformationState(
    val foods : List<Food> = emptyList(),
    val food : Food? = null,
    val searchQuery: String = ""
)
