package com.bangkit23.hidupsehat.presentation.screen.exercise.yoga

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.SheetState
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.bangkit23.hidupsehat.presentation.screen.exercise.component.DetailItemExercise
import com.bangkit23.hidupsehat.presentation.screen.exercise.component.ListItemExercise
import com.bangkit23.hidupsehat.presentation.screen.exercise.component.LoadingShimmerExercise
import com.bangkit23.hidupsehat.presentation.screen.exercise.model.Exercise
import com.bangkit23.hidupsehat.presentation.ui.theme.HidupSehatTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun YogaScreen(
    moveToExercisePlay: (Exercise) -> Unit,
    viewModel: YogaViewModel = hiltViewModel(),
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    LaunchedEffect(key1 = Unit) {
        viewModel.onEvent(YogaEvent.Refresh)
    }

    if (state.isLoading) {
        LoadingShimmerExercise(
            modifier = Modifier
                .padding(vertical = 32.dp, horizontal = 16.dp)
        )
    } else {
        YogaContent(
            flexibilityExercises = state.flexibilityExercises,
            strengthExercises = state.strengthExercises,
            recoveryExercises = state.recoveryExercises,
            clickedExercise = state.clickedExercise,
            openBottomSheet = state.openBottomSheet,
            onStartClicked = moveToExercisePlay,
            onItemClicked = {
                viewModel.onEvent(YogaEvent.OpenSheet(it))
            },
            onDismissSheet = {
                viewModel.onEvent(YogaEvent.DismissSheet)
            },
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun YogaContent(
    flexibilityExercises: List<Exercise>,
    strengthExercises: List<Exercise>,
    recoveryExercises: List<Exercise>,
    clickedExercise: Exercise,
    openBottomSheet: Boolean,
    onDismissSheet: () -> Unit,
    onItemClicked: (Exercise) -> Unit,
    onStartClicked: (Exercise) -> Unit,
    modifier: Modifier = Modifier,
    bottomSheetState: SheetState = rememberModalBottomSheetState(),
) {
    Column(
        modifier = modifier.fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {
        Spacer(Modifier.height(32.dp))
        ListItemExercise(
            headerText = "Untuk Melatih Kelenturan",
            data = flexibilityExercises,
            onItemClicked = onItemClicked
        )
        Spacer(Modifier.height(16.dp))
        ListItemExercise(
            headerText = "Untuk Melatih Kekuatan",
            data = strengthExercises,
            onItemClicked = onItemClicked
        )
        Spacer(Modifier.height(16.dp))
        ListItemExercise(
            headerText = "Untuk Pereda Sakit & Kebugaran",
            data = recoveryExercises,
            onItemClicked = onItemClicked
        )
        Spacer(Modifier.height(16.dp))
    }

    if (openBottomSheet) {
        ModalBottomSheet(
            onDismissRequest = { onDismissSheet() },
            sheetState = bottomSheetState
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Column {
                    Text(
                        text = clickedExercise.title,
                        style = MaterialTheme.typography.titleLarge
                    )
                    Text(text = "${clickedExercise.poses.size} Pose \u2022 ${clickedExercise.caloriesBurned} kal")
                }
                Button(
                    onClick = {
                        onStartClicked(clickedExercise)
                        onDismissSheet()
                    },
                    contentPadding = ButtonDefaults.ButtonWithIconContentPadding
                ) {
                    Icon(
                        Icons.Default.PlayArrow,
                        contentDescription = null,
                        modifier = Modifier.size(
                            ButtonDefaults.IconSize
                        )
                    )
                    Spacer(modifier = Modifier.size(ButtonDefaults.IconSpacing))
                    Text(text = "Mulai")
                }
            }
            LazyColumn {
                items(items = clickedExercise.poses) {
                    DetailItemExercise(
                        icon = it.image,
                        title = it.title,
                        description = it.detail
                    )
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview(showBackground = true)
@Composable
fun YogaContentPreview() {
    HidupSehatTheme {
        YogaContent(
            flexibilityExercises = emptyList(),
            strengthExercises = emptyList(),
            recoveryExercises = emptyList(),
            clickedExercise = Exercise("", "", "", poses = emptyList()),
            openBottomSheet = false,
            onDismissSheet = {},
            onItemClicked = {},
            onStartClicked = {},
        )
    }
}