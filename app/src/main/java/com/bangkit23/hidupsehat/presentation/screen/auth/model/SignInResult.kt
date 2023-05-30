package com.bangkit23.hidupsehat.presentation.screen.auth.model

data class SignInResult(
    val data: UserData?,
    val errorMessage: String?
)

data class UserData(
    val userId: String,
    val username: String?,
    val profilePictureUrl: String?,
    val isNewUser: Boolean = false,
)
