package com.bangkit23.hidupsehat.presentation.screen.exercise.workout

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.bangkit23.hidupsehat.presentation.components.UnderDevelopmentInfo

@Composable
fun WorkoutScreen() {
    Box(
        modifier = Modifier
            .fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        UnderDevelopmentInfo()
    }
}

@Preview
@Composable
fun WorkoutScreenPrev() {
    WorkoutScreen()
}