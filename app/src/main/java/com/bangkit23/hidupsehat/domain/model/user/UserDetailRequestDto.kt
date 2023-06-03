package com.bangkit23.hidupsehat.domain.model.user

data class UserDetailRequestDto(
    val username: String = "",
    val name: String = "",
    val contactNumber: String = "",
    val dateOfBirth: String = "",
    val age: Int = 0,
    val gender: String = "",
    val height: Int = 0,
    val weight: Int = 0,
    val target: String = "",
    val weightTarget: Int = 0,
)
