package com.bangkit23.hidupsehat.presentation.screen.preference

sealed class UserInformationEvent {
    data class CreateUserDetailPreferences(
        val username: String = "",
        val name: String,
        val contactNumber: String = "",
        val dateOfBirth: String,
        val age: Int,
        val gender: String,
        val height: Int,
        val weight: Int,
        val target: String,
        val weightTarget: Int,
    ) : UserInformationEvent()
    data class OnGenderIdChanged(val selectedGenderId: Int) : UserInformationEvent()
    data class OnAgeChanged(val age: String) : UserInformationEvent()
    data class OnHeightChanged(val height: String) : UserInformationEvent()
    data class OnCurrentWeightChanged(val currentWeight: String) : UserInformationEvent()
}