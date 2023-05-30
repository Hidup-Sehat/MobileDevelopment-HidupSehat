package com.bangkit23.hidupsehat.presentation.screen.exercise.model

import com.bangkit23.hidupsehat.presentation.screen.exercise_play.model.PersonBodyAngle

data class Pose(
    val image: String,
    val title: String,
    val detail: String,
    val personBodyAngle: PersonBodyAngle,
)
