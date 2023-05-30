package com.bangkit23.hidupsehat.presentation.screen.exercise_play

import com.bangkit23.hidupsehat.presentation.screen.exercise.model.Exercise

data class ExercisePlayState(
    val exercise: Exercise = Exercise("", "", "", poses = emptyList()),
    val currentPosePosition: Int = 0,
    val poseScore: Double = 0.0,
    val isExerciseDone: Boolean = false,
)
