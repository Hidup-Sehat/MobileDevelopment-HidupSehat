package com.bangkit23.hidupsehat.presentation.screen.food_information_detail

import com.bangkit23.hidupsehat.domain.model.food.Food

sealed class DetailFoodInformationEvent {
    data class OnGetFoodById(
        val name : String
    ) : DetailFoodInformationEvent()
    data class OnPortionSizeClick(
        val food : Food?
    ) : DetailFoodInformationEvent()

    data class OnDropDownItemClick(
        val food : Food?
    ) : DetailFoodInformationEvent()
}
