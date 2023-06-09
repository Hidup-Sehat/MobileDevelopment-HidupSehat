package com.bangkit23.hidupsehat.presentation.screen.exercise_play

import com.bangkit23.hidupsehat.presentation.screen.exercise.model.Exercise
import com.bangkit23.hidupsehat.presentation.screen.exercise_play.model.PersonBodyAngle

sealed class ExercisePlayEvent {
    data class AddExercise(val exercise: Exercise) : ExercisePlayEvent()
    data class SetScore(val actualAngle: PersonBodyAngle) : ExercisePlayEvent()
    object ResetScore : ExercisePlayEvent()
    data class OnPosePerfect(val currentPosePosition: Int) : ExercisePlayEvent()
    data class OnShowHideExitDialog(val isExitDialogShow: Boolean) : ExercisePlayEvent()
}