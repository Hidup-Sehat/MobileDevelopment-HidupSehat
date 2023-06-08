package com.bangkit23.hidupsehat.presentation.screen.splash

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bangkit23.hidupsehat.domain.usecase.auth.AuthUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
    private val authUseCase: AuthUseCase,
) : ViewModel() {

    var isLoggedIn = mutableStateOf(true)
        private set

    init {
        viewModelScope.launch {
            authUseCase.getSignedUser().collect {
                isLoggedIn.value = it != null && it.isNewUser == false
            }
        }
    }
}