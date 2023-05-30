package com.bangkit23.hidupsehat.presentation.common

import androidx.lifecycle.ViewModel
import com.bangkit23.hidupsehat.presentation.screen.exercise.model.Exercise
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class ExerciseSharedViewModel @Inject constructor() : ViewModel() {

    private val _exercise = MutableStateFlow(Exercise(title = "", description = "", image = "", poses = emptyList()))
    val exercise = _exercise.asStateFlow()

    fun setExercise(exercise: Exercise) {
        _exercise.value = exercise
    }
}