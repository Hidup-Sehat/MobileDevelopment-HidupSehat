package com.bangkit23.hidupsehat.presentation.screen.exercise.model

data class Exercise(
    val title : String = "",
    val description : String = "",
    val image : String = "",
    val cardColor: Int = 0,
    val poses: List<Pose> = emptyList(),
)
