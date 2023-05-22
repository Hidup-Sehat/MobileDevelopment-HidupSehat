package com.bangkit23.hidupsehat.presentation.screen.preference.model

data class UserPreference(
    val targetChoiceId: Int,
    val targetWeight: String,
    val genderId: Int,
    val userHeight: String,
    val userWeight: String,
    val userAge: String,
)
