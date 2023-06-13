package com.bangkit23.hidupsehat.presentation.screen.update_user_info

data class UpdateUserInfoState(
    val calorieNeeds: String = "",
    val calorieBurned: String = "",
    val sleepNeeds: Int = 0,
    val waterNeeds: Int = 0,
)