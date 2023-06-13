package com.bangkit23.hidupsehat.presentation.screen.mental_health

import com.bangkit23.hidupsehat.presentation.screen.exercise.model.Exercise

data class MentalHealthState(
    val isLoading: Boolean = false,
    val errorMessage: String? = null,
    val recommendationActivities: List<Exercise> = emptyList(),
    val greetingMessage: String = "",
)