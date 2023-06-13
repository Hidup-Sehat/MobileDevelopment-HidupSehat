package com.bangkit23.hidupsehat.presentation.screen.profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bangkit23.hidupsehat.domain.usecase.auth.AuthUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val authUseCase: AuthUseCase,
) : ViewModel() {

    private val _state = MutableStateFlow(ProfileState())
    val state = _state.asStateFlow()

    init {
        getUserProfile()
    }

    fun onEvent(event: ProfileEvent) {
        when (event) {
            is ProfileEvent.OnLogOut -> {
                logOut()
            }
        }
    }

    private fun getUserProfile() = viewModelScope.launch{
        authUseCase.getSignedUser().collect { userData ->
            _state.update {
                it.copy(
                    userData = userData
                )
            }
        }
    }

    private fun logOut() = viewModelScope.launch {
        authUseCase.signOut()
        _state.update {
            it.copy(
                isLoggedOut = true
            )
        }
    }
}