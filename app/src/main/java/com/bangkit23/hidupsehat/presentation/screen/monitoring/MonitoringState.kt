package com.bangkit23.hidupsehat.presentation.screen.monitoring

import com.bangkit23.hidupsehat.domain.model.food.Food
import com.bangkit23.hidupsehat.presentation.screen.monitoring.model.Nutrition

data class MonitoringState(
    val isLoading: Boolean = false,
    val errorMessage: String? = null,
    val historyFoods: List<Food> = emptyList(),
    val historyNutrition: List<Nutrition> = emptyList(),
)
