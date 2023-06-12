package com.bangkit23.hidupsehat.presentation.screen.auth.login

import android.content.Intent

sealed class LoginEvent {
    data class OnEmailChanged(val email: String) : LoginEvent()
    data class OnPasswordChanged(val password: String) : LoginEvent()
    data class LoginWithEmailPassword(val email: String, val password: String) : LoginEvent()
    data class SignInGoogleWithIntent(val intent: Intent) : LoginEvent()
    data class SetLoadingState(val isLoading: Boolean) : LoginEvent()
    object ResetState : LoginEvent()
}