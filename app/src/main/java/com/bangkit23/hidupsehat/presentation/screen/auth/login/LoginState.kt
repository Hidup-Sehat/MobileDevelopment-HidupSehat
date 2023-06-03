package com.bangkit23.hidupsehat.presentation.screen.auth.login

import com.bangkit23.hidupsehat.presentation.screen.auth.model.SignInResult

data class LoginState(
    val loading: Boolean = false,
    val signInSuccessful: Boolean = false,
    val signInError: String? = null,
    val signInResult: SignInResult? = null,
    val email: String = "",
    val password: String = "",
)
