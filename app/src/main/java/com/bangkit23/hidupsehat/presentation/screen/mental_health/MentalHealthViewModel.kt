package com.bangkit23.hidupsehat.presentation.screen.mental_health

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bangkit23.hidupsehat.domain.model.activity.ActivityItem
import com.bangkit23.hidupsehat.domain.usecase.activity.ActivityUseCase
import com.bangkit23.hidupsehat.presentation.screen.exercise.common.toExercise
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
) : ViewModel() {

    private val _state = MutableStateFlow(MentalHealthState())
    val state = _state.asStateFlow()

    init {
        getRecommendationActivities()
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