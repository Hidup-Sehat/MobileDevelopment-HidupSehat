package com.bangkit23.hidupsehat.presentation.screen.mental_health

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bangkit23.hidupsehat.domain.model.activity.ActivityItem
import com.bangkit23.hidupsehat.domain.usecase.activity.ActivityUseCase
import com.bangkit23.hidupsehat.domain.usecase.auth.AuthUseCase
import com.bangkit23.hidupsehat.presentation.screen.exercise.common.toExercise
import com.bangkit23.hidupsehat.util.DateHelper.getGreetingMessage
import com.bangkit23.hidupsehat.util.Result
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MentalHealthViewModel @Inject constructor(
    private val activityUseCase: ActivityUseCase,
    private val authUseCase: AuthUseCase,
) : ViewModel() {

    private val _state = MutableStateFlow(MentalHealthState())
    val state = _state.asStateFlow()

    init {
        getUserGreetingMessage()
        getRecommendationActivities()
    }

    private fun getUserGreetingMessage() = viewModelScope.launch {
        authUseCase.getSignedUser().collect { user ->
            _state.update {
                it.copy(
                    greetingMessage = "${getGreetingMessage()}, ${user?.username?.split(" ")?.getOrNull(0)}"
                )
            }
        }
    }

    private fun getRecommendationActivities() = viewModelScope.launch {
        activityUseCase.getYogaActivities().collect { result ->
            when (result) {
                is Result.Loading -> {
                    _state.update {
                        it.copy(isLoading = true)
                    }
                }
                is Result.Success -> {
                    val activities = result.data.map(ActivityItem::toExercise)
                    _state.update {
                        it.copy(
                            isLoading = false,
                            recommendationActivities = activities
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
            }
        }
    }
}