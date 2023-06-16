package com.bangkit23.hidupsehat.presentation.screen.food_information

sealed class FoodInformationEvent {
    data class OnQueryFoodChange(
        val query: String,
    ) : FoodInformationEvent()
}
