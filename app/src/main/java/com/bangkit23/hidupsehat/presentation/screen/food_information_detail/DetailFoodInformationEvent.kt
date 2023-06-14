package com.bangkit23.hidupsehat.presentation.screen.food_information_detail

sealed class DetailFoodInformationEvent {
    data class OnGetFoodById(
        val name : String
    ) : DetailFoodInformationEvent()
}
