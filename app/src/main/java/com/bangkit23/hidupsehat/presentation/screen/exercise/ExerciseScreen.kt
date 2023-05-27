@file:OptIn(ExperimentalPagerApi::class)

package com.bangkit23.hidupsehat.presentation.screen.exercise

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.selection.toggleable
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.ListItem
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.bangkit23.hidupsehat.presentation.screen.exercise.workout.WorkoutScreen
import com.bangkit23.hidupsehat.presentation.screen.exercise.yoga.YogaScreen
import com.google.accompanist.pager.ExperimentalPagerApi
import kotlinx.coroutines.launch


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ExerciseScreen() {
    Column {
        TopAppBar(
            navigationIcon = {
                Icon(imageVector = Icons.Default.ArrowBack, contentDescription = null)
            },
            title = { Text(modifier = Modifier.padding(start = 16.dp), text = "Yoga & Workouts") })
        Tabs()
    }
}


@Composable
fun Tabs() {
    var state by remember { mutableStateOf(0) }
    val titles = listOf("Yoga", "Workout")
    Column {
        TabRow(selectedTabIndex = state) {
            titles.forEachIndexed { index, title ->
                Tab(selected = state == index, onClick = { state = index },
                    text = { Text(text = title) })
            }
        }
        when (state) {
            0 -> YogaScreen()
            1 -> WorkoutScreen()
        }
    }
}
@Preview(showBackground = true)
@Composable
fun ExerciseScreenPrev() {
    ExerciseScreen()
}

