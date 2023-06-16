package com.bangkit23.hidupsehat.presentation.screen.exercise.yoga

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bangkit23.hidupsehat.domain.model.activity.ActivityItem
import com.bangkit23.hidupsehat.domain.usecase.activity.ActivityUseCase
import com.bangkit23.hidupsehat.presentation.screen.exercise.common.toExercise
import com.bangkit23.hidupsehat.presentation.screen.exercise.model.Exercise
import com.bangkit23.hidupsehat.util.Result
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class YogaViewModel @Inject constructor(
    private val activityUseCase: ActivityUseCase,
) : ViewModel() {

    private var _state = MutableStateFlow(YogaState())
    val state = _state.asStateFlow()

    init {
        getListExercises()
    }

    fun onEvent(event: YogaEvent) {
        when (event) {
            is YogaEvent.Refresh -> {
//                getListExercises()
            }
            is YogaEvent.OpenSheet -> {
                _state.update {
                    it.copy(
                        openBottomSheet = true,
                        clickedExercise = event.exercise
                    )
                }
            }
            is YogaEvent.DismissSheet -> {
                _state.update {
                    it.copy(
                        openBottomSheet = false
                    )
                }
            }
        }
    }

    private fun getListExercises() = viewModelScope.launch {
        activityUseCase.getYogaActivities().collect { result ->
            when (result) {
                is Result.Loading -> {
                    _state.update {
                        it.copy(
                            isLoading = true
                        )
                    }
                }
                is Result.Success -> {
                    val flexibility = result.data.filterByCategory("Kelenturan")
                    val strength = result.data.filterByCategory("Kekuatan")
                    val fitness = result.data.filterByCategory("Keseimbangan")
                    _state.update {
                        it.copy(
                            isLoading = false,
                            flexibilityExercises = flexibility,
                            strengthExercises = strength,
                            recoveryExercises = fitness,
                        )
                    }
                }
                is Result.Error -> {
                    _state.update {
                        it.copy(
                            isLoading = false,
                            error = result.message
                        )
                    }
                }
            }
        }
    }

    private fun List<ActivityItem>.filterByCategory(category: String): List<Exercise> =
        filter { it.category == category }.map(ActivityItem::toExercise)
}