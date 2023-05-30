package com.bangkit23.hidupsehat.presentation.screen.exercise_play

import android.util.Log
import androidx.lifecycle.ViewModel
import com.bangkit23.hidupsehat.presentation.screen.exercise_play.model.PersonBodyAngle
import com.bangkit23.hidupsehat.util.calculateSimilarity
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class ExercisePlayViewModel @Inject constructor() : ViewModel() {

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
                //Start-Timer
            }
            is ExercisePlayEvent.OnTimerEnd -> {
                _state.update {
                    val posesSize = it.exercise.poses.size
                    Log.d("ONTIMER-END", "onEvent: $posesSize | ${event.currentPosePosition} | ${event.currentPosePosition == posesSize - 1}")
                    it.copy(
                        currentPosePosition = if (event.currentPosePosition != posesSize - 1)
                            event.currentPosePosition + 1 else posesSize - 1,
                        isExerciseDone = event.currentPosePosition == posesSize - 1
                    )
                }
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
        }
    }

    private fun PersonBodyAngle.getSimilarityScore(expectedScore: PersonBodyAngle): Double {
        return calculateSimilarity(this, expectedScore)
    }
}