package com.bangkit23.hidupsehat.presentation.screen.exercise.yoga

import com.bangkit23.hidupsehat.presentation.screen.exercise.model.Exercise

data class YogaState(
    val isLoading: Boolean = false,
    val error: String? = null,
    val lastActivities: List<Exercise> = emptyList(),
    val flexibilityExercises: List<Exercise> = emptyList(),
    val strengthExercises: List<Exercise> = emptyList(),
    val recoveryExercises: List<Exercise> = emptyList(),
    val clickedExercise: Exercise = Exercise(),
    val openBottomSheet: Boolean = false,
)
