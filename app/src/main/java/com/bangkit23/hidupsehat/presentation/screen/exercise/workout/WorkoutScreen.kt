package com.bangkit23.hidupsehat.presentation.screen.exercise.workout

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun WorkoutScreen() {
    Box(modifier = Modifier
        .fillMaxSize(),
    contentAlignment = Alignment.Center
        ){
        Text(text = "Workout")
    }
}

@Preview
@Composable
fun WorkoutScreenPrev() {
    WorkoutScreen()
}