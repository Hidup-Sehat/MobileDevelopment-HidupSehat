package com.bangkit23.hidupsehat.presentation.screen.food_information

import com.bangkit23.hidupsehat.domain.model.food.Food

sealed class FoodInformationEvent {
    data class OnGetAllFeed(
        val data : List<Food>
    ) : FoodInformationEvent()

}
