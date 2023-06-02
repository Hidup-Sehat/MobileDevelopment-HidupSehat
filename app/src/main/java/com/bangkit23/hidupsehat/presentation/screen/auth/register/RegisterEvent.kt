package com.bangkit23.hidupsehat.presentation.screen.auth.register

import android.content.Intent

sealed class RegisterEvent {
    data class OnNameChanged(val name: String) : RegisterEvent()
    data class OnEmailChanged(val email: String) : RegisterEvent()
    data class OnPasswordChanged(val password: String) : RegisterEvent()
    data class OnPasswordConfirmationChanged(val confirmationPassword: String) : RegisterEvent()
    data class RegisterWithEmailPassword(
        val name: String,
        val email: String,
        val password: String,
    ) : RegisterEvent()
    data class SignInGoogleWithIntent(val intent: Intent) : RegisterEvent()
    object ResetState : RegisterEvent()
}