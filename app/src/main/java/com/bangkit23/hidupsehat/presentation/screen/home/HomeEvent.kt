package com.bangkit23.hidupsehat.presentation.screen.home

import com.bangkit23.hidupsehat.domain.model.food.Food
import com.bangkit23.hidupsehat.presentation.screen.home.model.Feel

sealed class HomeEvent {
    data class OnTodayEmotionChosen(
        val feel: Feel?
    ) : HomeEvent()
    data class OnManualFoodAdded(
        val food: Food
    ) : HomeEvent()
}