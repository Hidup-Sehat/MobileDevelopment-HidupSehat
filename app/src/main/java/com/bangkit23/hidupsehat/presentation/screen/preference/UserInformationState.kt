package com.bangkit23.hidupsehat.presentation.screen.preference

data class UserInformationState(
    val loading: Boolean = false,
    val errorMessage: String? = null,
    val success: Boolean = false,
    val selectedGenderId: Int = -1,
    val age: String = "",
    val height: String = "",
    val currentWeight: String = "",
)