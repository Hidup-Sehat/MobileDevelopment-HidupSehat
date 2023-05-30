package com.bangkit23.hidupsehat.presentation.screen.exercise

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.bangkit23.hidupsehat.presentation.screen.exercise.model.Exercise
import com.bangkit23.hidupsehat.presentation.screen.exercise.workout.WorkoutScreen
import com.bangkit23.hidupsehat.presentation.screen.exercise.yoga.YogaScreen

@Composable
fun ExerciseScreen(
    onNavigateUp: () -> Unit,
    moveToExercisePlay: (Exercise) -> Unit,
) {
    ExerciseContent(
        onNavigateUp = onNavigateUp,
        moveToExercisePlay = moveToExercisePlay
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ExerciseContent(
    onNavigateUp: () -> Unit,
    moveToExercisePlay: (Exercise) -> Unit,
    modifier: Modifier = Modifier,
) {
    Scaffold(
        modifier = modifier,
        topBar = {
            TopAppBar(
                navigationIcon = {
                    IconButton(
                        onClick = onNavigateUp,
                    ) {
                        Icon(
                            imageVector = Icons.Rounded.ArrowBack,
                            contentDescription = "Back"
                        )
                    }
                },
                title = { Text("Yoga & Workouts") }
            )
        }
    ) { contentPadding ->
        Tabs(
            modifier = modifier.padding(contentPadding),
            moveToExercisePlay = moveToExercisePlay
        )
    }
}

@Composable
fun Tabs(
    moveToExercisePlay: (Exercise) -> Unit,
    modifier: Modifier = Modifier
) {
    var state by remember { mutableStateOf(0) }
    val titles = listOf("Yoga", "Workout")
    Column(modifier = modifier) {
        TabRow(
            selectedTabIndex = state,
        ) {
            titles.forEachIndexed { index, title ->
                Tab(
                    selected = state == index,
                    onClick = { state = index },
                    text = { Text(text = title) }
                )
            }
        }
        when (state) {
            0 -> YogaScreen(
                moveToExercisePlay = moveToExercisePlay
            )
            1 -> WorkoutScreen()
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ExerciseScreenPrev() {
    ExerciseScreen(
        onNavigateUp = {},
        moveToExercisePlay = {},
    )
}

