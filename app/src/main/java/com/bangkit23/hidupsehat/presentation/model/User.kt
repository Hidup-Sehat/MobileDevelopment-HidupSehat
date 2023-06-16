package com.bangkit23.hidupsehat.presentation.model

data class User(
    val userId: String? = null,
    val name: String? = null,
    val photoUrl: String? = null,
    val targetUser: Int? = null,
    val targetWeight: Int? = null,
    val gender: Int? = null,
    val age: Int? = null,
    val height: Int? = null,
    val currentWeight: Int? = null,
)