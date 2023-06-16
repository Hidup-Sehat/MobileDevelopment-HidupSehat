package com.bangkit23.hidupsehat.domain.model.user

data class UserDetail(
    val photoUrl: String? = null,
    val currentWeight: Int? = null,
    val gender: String? = null,
    val name: String? = null,
    val targetWeight: Int? = null,
    val userId: String? = null,
    val targetUser: Int? = null,
    val age: Int? = null,
    val height: Int? = null
)
