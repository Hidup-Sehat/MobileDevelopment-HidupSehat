package com.bangkit23.hidupsehat.presentation.screen.update_user_info

sealed class UpdateUserInfoEvent {
    data class SaveInitialInfo(
        val sleepNeeds: Int,
        val waterNeeds: Int,
    ) : UpdateUserInfoEvent()
    data class OnWaterNeedsChanged(val waterNeeds: Int) : UpdateUserInfoEvent()
    data class OnSleepNeedsChanged(val sleepNeeds: Int) : UpdateUserInfoEvent()
    object ResetState : UpdateUserInfoEvent()
    object SaveUpdatedInfo : UpdateUserInfoEvent()
}