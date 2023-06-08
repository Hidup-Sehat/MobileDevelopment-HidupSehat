package com.bangkit23.hidupsehat.presentation.screen.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bangkit23.hidupsehat.domain.usecase.auth.AuthUseCase
import com.bangkit23.hidupsehat.domain.usecase.user.UserUseCase
import com.bangkit23.hidupsehat.util.Result
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val userUseCase: UserUseCase,
    private val authUseCase: AuthUseCase,
) : ViewModel() {

    private val _state = MutableStateFlow(HomeState())
    val state = _state.asStateFlow()

    init {
        getUserData()
        getUserNeeds()
    }

    fun onEvent(event: HomeEvent) {
        when (event) {
            is HomeEvent.OnManualFoodAdded -> {

            }
            is HomeEvent.OnTodayEmotionChosen -> {
                _state.update {
                    it.copy(
                        chosenEmotion = event.feel
                    )
                }
            }
        }
    }

    private fun getUserData() = viewModelScope.launch {
        authUseCase.getSignedUser().collect { userData ->
            _state.update {
                it.copy(
                    userData = userData
                )
            }
        }
    }

    private fun getUserNeeds() = viewModelScope.launch {
        userUseCase.getUserNeeds().collect { result ->
            when (result) {
                is Result.Loading -> {
                    _state.update {
                        it.copy(
                            isLoading = true
                        )
                    }
                }
                is Result.Error -> {
                    _state.update {
                        it.copy(
                            isLoading = false,
                            errorMessage = result.message
                        )
                    }
                }
                is Result.Success -> {
                    _state.update {
                        it.copy(
                            isLoading = false,
                            userNeeds = result.data
                        )
                    }
                }
            }
        }
    }
}