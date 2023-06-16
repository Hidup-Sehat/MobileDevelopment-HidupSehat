package com.bangkit23.hidupsehat.presentation.screen.auth.register

import com.bangkit23.hidupsehat.presentation.screen.auth.model.SignInResult

data class RegisterState(
    val loading: Boolean = false,
    val signInSuccessful: Boolean = false,
    val signInError: String? = null,
    val signInResult: SignInResult? = null,
    val name: String = "",
    val email: String = "",
    val password: String = "",
    val confirmationPassword: String = "",
)