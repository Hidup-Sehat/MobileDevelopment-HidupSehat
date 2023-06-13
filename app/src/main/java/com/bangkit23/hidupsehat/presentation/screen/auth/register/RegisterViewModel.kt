package com.bangkit23.hidupsehat.presentation.screen.auth.register

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
class RegisterViewModel @Inject constructor(
    private val authUseCase: AuthUseCase,
) : ViewModel() {

    private val _state = MutableStateFlow(RegisterState())
    val state = _state.asStateFlow()

    fun onEvent(event: RegisterEvent) {
        when (event) {
            is RegisterEvent.OnNameChanged -> {
                _state.update {
                    it.copy(
                        name = event.name
                    )
                }
            }
            is RegisterEvent.OnEmailChanged -> {
                _state.update {
                    it.copy(
                        email = event.email
                    )
                }
            }
            is RegisterEvent.OnPasswordChanged -> {
                _state.update {
                    it.copy(
                        password = event.password
                    )
                }
            }
            is RegisterEvent.OnPasswordConfirmationChanged -> {
                _state.update {
                    it.copy(
                        confirmationPassword = event.confirmationPassword
                    )
                }
            }
            is RegisterEvent.RegisterWithEmailPassword -> {
                registerWithEmailPassword(event.name, event.email, event.password)
            }
            is RegisterEvent.SignInGoogleWithIntent -> {
                signInGoogleWithIntent(event.intent)
            }
            is RegisterEvent.ResetState -> {
                _state.update {
                    RegisterState()
                }
            }
            is RegisterEvent.SetLoadingState -> {
                _state.update {
                    it.copy(
                        loading = event.isLoading
                    )
                }
            }
        }
    }

    private fun registerWithEmailPassword(name: String, email: String, password: String) = viewModelScope.launch {
        authUseCase.registerWithEmail(name, email, password)
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
                                loading = true
                            )
                        }
                    }
                    is Result.Success -> {
                        _state.update {
                            it.copy(
                                signInSuccessful = true,
                                loading = false
                            )
                        }
                    }
                }
            }
    }

    private fun signInGoogleWithIntent(intent: Intent) = viewModelScope.launch {
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