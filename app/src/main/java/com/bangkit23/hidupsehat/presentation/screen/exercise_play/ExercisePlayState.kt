package com.bangkit23.hidupsehat.presentation.screen.exercise_play

import com.bangkit23.hidupsehat.presentation.screen.exercise.model.Exercise

data class ExercisePlayState(
    val exercise: Exercise = Exercise(),
    val currentPosePosition: Int = 0,
    val poseScore: Double = 0.0,
    val isExerciseDone: Boolean = false,
    val isTimerStarted: Boolean = false,
    val isExitDialogShow: Boolean = false,
    val timer: Int = -1,
    val exercisePoint: Int = 25
)
