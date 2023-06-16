package com.bangkit23.hidupsehat.presentation.screen.update_user_info

data class UpdateUserInfoState(
    val sleepNeeds: Int = 0,
    val waterNeeds: Int = 0,
    val isLoading: Boolean = false,
    val errorMessage: String? = null,
    val isSuccess: Boolean = false,
)