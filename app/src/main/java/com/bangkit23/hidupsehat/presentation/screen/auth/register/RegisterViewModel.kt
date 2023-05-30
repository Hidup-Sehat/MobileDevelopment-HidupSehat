package com.bangkit23.hidupsehat.presentation.screen.auth.register

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.bangkit23.hidupsehat.presentation.screen.auth.login.LoginState
import com.bangkit23.hidupsehat.presentation.screen.auth.model.SignInResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor() : ViewModel() {

    var name = mutableStateOf("")
        private set

    var email = mutableStateOf("")
        private set

    var password = mutableStateOf("")
        private set

    var confirmationPassword = mutableStateOf("")
        private set

    fun setName(name: String) {
        this.name.value = name
    }

    fun setEmail(email: String) {
        this.email.value = email
    }

    fun setPassword(password: String) {
        this.password.value = password
    }

    fun setConfirmationPassword(confirmationPassword: String) {
        this.confirmationPassword.value = confirmationPassword
    }

    private val _state = MutableStateFlow(LoginState())
    val state = _state.asStateFlow()

    fun onSignInResult(signInResult: SignInResult) {
        _state.update {
            it.copy(
                isSignInSuccessful = signInResult.data != null,
                signInError = signInResult.errorMessage,
                userData = signInResult.data?.copy(
                    username = name.value
                )
            )
        }
    }

    fun resetState() {
        _state.update { LoginState() }
    }
}