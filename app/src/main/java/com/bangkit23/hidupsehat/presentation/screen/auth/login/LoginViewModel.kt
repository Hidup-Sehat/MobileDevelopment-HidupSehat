package com.bangkit23.hidupsehat.presentation.screen.auth.login

import android.content.Intent
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bangkit23.hidupsehat.domain.usecase.auth.AuthUseCase
import com.bangkit23.hidupsehat.util.Result
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val authUseCase: AuthUseCase,
) : ViewModel() {

    private val _state = MutableStateFlow(LoginState())
    val state = _state.asStateFlow()

    fun onEvent(event: LoginEvent) {
        when (event) {
            is LoginEvent.LoginWithEmailPassword -> {

            }
            is LoginEvent.OnEmailChanged -> {
                _state.update {
                    it.copy(
                        email = event.email
                    )
                }
            }
            is LoginEvent.OnPasswordChanged -> {
                _state.update {
                    it.copy(
                        password = event.password
                    )
                }
            }
            is LoginEvent.SignInGoogleWithIntent -> {
                signInWithIntent(event.intent)
            }
            is LoginEvent.ResetState -> {
                _state.update {
                    LoginState()
                }
            }
            is LoginEvent.SetLoadingState -> {
                _state.update {
                    it.copy(
                        loading = event.isLoading
                    )
                }
            }
        }
    }

    private fun signInWithIntent(intent: Intent) {
        viewModelScope.launch {
            authUseCase.signInWithIntent(intent)
                .collect { result ->
                    when (result) {
                        is Result.Error -> {
                            _state.update {
                                it.copy(
                                    signInError = result.message,
                                    loading = false
                                )
                            }
                        }
                        is Result.Loading -> {
                            _state.update {
                                it.copy(
                                    loading = true,
                                )
                            }
                        }
                        is Result.Success -> {
                            _state.update {
                                it.copy(
                                    signInResult = result.data,
                                    signInSuccessful = true,
                                    loading = false,
                                )
                            }
                        }
                    }
                }
        }
    }
}