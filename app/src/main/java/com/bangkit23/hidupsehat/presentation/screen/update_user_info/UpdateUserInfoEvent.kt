package com.bangkit23.hidupsehat.presentation.screen.update_user_info

sealed class UpdateUserInfoEvent {
    data class SaveInitialInfo(
        val calorieNeeds: String,
        val calorieBurned: String,
    ) : UpdateUserInfoEvent()
    data class OnCalorieNeedsChanged(val calorieNeeds: String) : UpdateUserInfoEvent()
    data class OnCalorieBurnedChanged(val calorieBurned: String) : UpdateUserInfoEvent()
    object ResetState : UpdateUserInfoEvent()
    object SaveUpdatedInfo : UpdateUserInfoEvent()
}