package com.bangkit23.hidupsehat.presentation.screen.auth.login

import com.bangkit23.hidupsehat.presentation.screen.auth.model.UserData

data class LoginState(
    val isSignInSuccessful: Boolean = false,
    val signInError: String? = null,
    val userData: UserData? = null,
)
