package com.bangkit23.hidupsehat.presentation.screen.exercise_play

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bangkit23.hidupsehat.domain.usecase.user.UserUseCase
import com.bangkit23.hidupsehat.presentation.screen.exercise_play.model.PersonBodyAngle
import com.bangkit23.hidupsehat.util.Result
import com.bangkit23.hidupsehat.util.calculateSimilarity
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ExercisePlayViewModel @Inject constructor(
    private val userUseCase: UserUseCase,
) : ViewModel() {

    private val _state = MutableStateFlow(ExercisePlayState())
    val state = _state.asStateFlow()

    fun onEvent(event: ExercisePlayEvent) {
        when (event) {
            is ExercisePlayEvent.AddExercise -> {
                _state.update {
                    it.copy(
                        exercise = event.exercise
                    )
                }
            }
            is ExercisePlayEvent.OnPosePerfect -> {
                countDownTimer(
                    currentPosePosition = event.currentPosePosition
                ).start()
            }
            is ExercisePlayEvent.ResetScore -> {
                _state.update {
                    it.copy(
                        poseScore = 0.0
                    )
                }
            }
            is ExercisePlayEvent.SetScore -> {
                _state.update {
                    it.copy(
                        poseScore = event.actualAngle.getSimilarityScore(
                            it.exercise.poses[it.currentPosePosition].personBodyAngle
                        )
                    )
                }
            }
            is ExercisePlayEvent.OnShowHideExitDialog -> {
                _state.update {
                    it.copy(
                        isExitDialogShow = event.isExitDialogShow
                    )
                }
            }
        }
    }

    private fun PersonBodyAngle.getSimilarityScore(expectedScore: PersonBodyAngle?): Double {
        return if (expectedScore != null) {
            calculateSimilarity(this, expectedScore)
        } else 0.0
    }

    private fun countDownTimer(currentPosePosition: Int) = viewModelScope.launch {
        (8 downTo 0).forEach { timer ->
            _state.update {
                it.copy(
                    isTimerStarted = true,
                    timer = timer
                )
            }
            delay(1000)
        }
        _state.update {
            val posesSize = it.exercise.poses.size
            val isExerciseDone = currentPosePosition == posesSize - 1
            if (isExerciseDone) addUserPoints()
            it.copy(
                currentPosePosition = if (currentPosePosition != posesSize - 1)
                    currentPosePosition + 1 else posesSize - 1,
                poseScore = 0.0,
                timer = -1,
                isTimerStarted = false
            )
        }
    }

    private fun addUserPoints() = viewModelScope.launch {
        userUseCase.addUserPoints(25).collect { result ->
            when (result) {
                is Result.Loading -> {}
                is Result.Success -> {
                    _state.update {
                        it.copy(
                            isExerciseDone = true,
                            exercisePoint = result.data.pointsAdded ?: 0
                        )
                    }
                }
                is Result.Error -> {
                    _state.update {
                        it.copy(
                            isExerciseDone = true,
                            exercisePoint = 0
                        )
                    }
                }
            }
        }
    }
}