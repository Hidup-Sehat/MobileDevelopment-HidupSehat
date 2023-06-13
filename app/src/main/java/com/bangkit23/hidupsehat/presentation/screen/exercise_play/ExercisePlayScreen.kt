package com.bangkit23.hidupsehat.presentation.screen.exercise_play

import android.Manifest
import android.util.Log
import android.view.SurfaceView
import android.widget.Toast
import androidx.activity.compose.BackHandler
import androidx.camera.core.CameraSelector
import androidx.camera.core.ImageAnalysis
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Cast
import androidx.compose.material.icons.rounded.Close
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.AsyncImage
import com.bangkit23.hidupsehat.presentation.screen.exercise.model.Exercise
import com.bangkit23.hidupsehat.presentation.screen.exercise_play.common.MoveNet
import com.bangkit23.hidupsehat.presentation.screen.exercise_play.common.PoseEstimationAnalyzer
import com.bangkit23.hidupsehat.presentation.screen.exercise_play.components.PoseScore
import com.bangkit23.hidupsehat.presentation.screen.exercise_play.model.PersonBodyAngle
import com.bangkit23.hidupsehat.presentation.screen.exercise_play.model.ml.Device
import com.bangkit23.hidupsehat.presentation.screen.point_popup.PointPopupDialog
import com.bangkit23.hidupsehat.util.executor
import com.bangkit23.hidupsehat.util.getCameraProvider
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.PermissionRequired
import com.google.accompanist.permissions.PermissionState
import com.google.accompanist.permissions.rememberPermissionState
import com.google.accompanist.systemuicontroller.SystemUiController
import com.google.accompanist.systemuicontroller.rememberSystemUiController

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun ExercisePlayScreen(
    exercise: Exercise,
    onNavigateUp: () -> Unit,
    viewModel: ExercisePlayViewModel = hiltViewModel(),
) {
    LaunchedEffect(key1 = Unit) {
        viewModel.onEvent(ExercisePlayEvent.AddExercise(exercise))
    }
    val state by viewModel.state.collectAsStateWithLifecycle()
    val context = LocalContext.current

    BackHandler(enabled = !state.isExitDialogShow) {
        viewModel.onEvent(ExercisePlayEvent.OnShowHideExitDialog(true))
    }

    LaunchedEffect(key1 = state.poseScore, key2 = state.isExerciseDone) {
        if (state.poseScore > 0.9) {
            viewModel.onEvent(ExercisePlayEvent.OnPosePerfect(state.currentPosePosition))
        }
    }

    ExercisePlayContent(
        score = state.poseScore,
        poseImage = exercise.poses[state.currentPosePosition].image,
        isTimerShouldStarted = state.isTimerStarted,
        timer = state.timer,
        onAngleChange = {
            if (state.poseScore <= 0.9) {
                viewModel.onEvent(ExercisePlayEvent.SetScore(it))
            }
        },
        onNoPerson = {
            viewModel.onEvent(ExercisePlayEvent.ResetScore)
        },
        onNavigateUp = {
            viewModel.onEvent(ExercisePlayEvent.OnShowHideExitDialog(true))
        },
        onScreenCastClick = {
            Toast.makeText(context, "Screen Cast is Under Development", Toast.LENGTH_SHORT)
                .show()
        }
    )

    if (state.isExitDialogShow) {
        ExitAlertDialog(
            onConfirm = onNavigateUp,
            onCancel = {
                viewModel.onEvent(ExercisePlayEvent.OnShowHideExitDialog(false))
            }
        )
    }

    if (state.isExerciseDone) {
        PointPopupDialog(
            points = state.exercisePoint,
            onDismissRequest = onNavigateUp
        )
    }
}

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun ExercisePlayContent(
    score: Double,
    poseImage: String,
    isTimerShouldStarted: Boolean,
    timer: Int,
    onAngleChange: (PersonBodyAngle) -> Unit,
    onNoPerson: () -> Unit,
    onNavigateUp: () -> Unit,
    onScreenCastClick: () -> Unit,
    modifier: Modifier = Modifier,
    cameraSelector: CameraSelector = CameraSelector.DEFAULT_FRONT_CAMERA,
    systemUiController: SystemUiController = rememberSystemUiController(),
    permissionState: PermissionState = rememberPermissionState(Manifest.permission.CAMERA),
) {
    val context = LocalContext.current
    val poseDetector by remember {
        mutableStateOf(
            MoveNet.create(context, Device.CPU)
        )
    }

    DisposableEffect(systemUiController) {
        systemUiController.apply {
            isStatusBarVisible = false
            isNavigationBarVisible = false
            isSystemBarsVisible = false
        }
        onDispose {
            systemUiController.apply {
                isStatusBarVisible = true
                isNavigationBarVisible = true
                isSystemBarsVisible = true
            }
        }
    }

    LaunchedEffect(Unit) {
        permissionState.launchPermissionRequest()
    }

    Box(modifier = modifier) {
        PermissionRequired(
            permissionState = permissionState,
            permissionNotGrantedContent = {},
            permissionNotAvailableContent = {}
        ) {
            val lifecycleOwner = LocalLifecycleOwner.current
            var surfaceView by remember { mutableStateOf(SurfaceView(context)) }
            val imageAnalyzer by remember {
                derivedStateOf {
                    ImageAnalysis.Builder()
                        .build()
                        .also {
                            it.setAnalyzer(
                                context.executor,
                                PoseEstimationAnalyzer(
                                    poseDetector, surfaceView, onAngleChange, onNoPerson
                                )
                            )
                        }
                }
            }
            Box(Modifier.fillMaxSize()) {
                SurfaceView(
                    modifier = Modifier.fillMaxSize(),
                    onSurface = { surfaceView = it }
                )
                PoseScore(
                    score = score,
                    modifier = Modifier.rotate(90f)
                        .align(Alignment.BottomStart)
                        .padding(0.dp)
                )
                AsyncImage(
                    model = poseImage,
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .align(Alignment.TopEnd)
                        .size(width = 300.dp, height = 200.dp)
                        .rotate(90f)
                        .offset(x = 48.dp, y = (-48).dp)
                )
                TopButtons(
                    onNavigateUp = onNavigateUp,
                    onScreenCastClick = onScreenCastClick,
                    modifier = Modifier
                        .rotate(90f)
                        .align(Alignment.BottomEnd)
                        .offset(x = (-16).dp, y = (-32).dp)
                )
                if (isTimerShouldStarted) {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier
                            .align(Alignment.Center)
                            .rotate(90f)
                    ) {
                        Text(
                            text = "Tahan Posisi",
                            style = MaterialTheme.typography.displayLarge,
                            color = Color.Red,
                            fontWeight = FontWeight.Bold,
                        )
                        Text(
                            text = "$timer",
                            style = MaterialTheme.typography.displayLarge,
                            color = Color.Red,
                            fontWeight = FontWeight.Bold,
                        )
                    }
                }
            }
            LaunchedEffect(surfaceView, imageAnalyzer) {
                val cameraProvider = context.getCameraProvider()
                try {
                    cameraProvider.unbindAll()
                    cameraProvider.bindToLifecycle(
                        lifecycleOwner,
                        cameraSelector,
                        imageAnalyzer
                    )
                } catch (e: Exception) {
                    Log.e("CameraCapture", "ScanFoodContent: $e")
                }
            }
        }
    }
}

@Composable
fun TopButtons(
    onNavigateUp: () -> Unit,
    onScreenCastClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .padding(16.dp)
    ) {
        IconButton(
            onClick = onScreenCastClick,
        ) {
            Icon(
                imageVector = Icons.Rounded.Cast,
                contentDescription = "Cast To Tv",
                tint = Color.White
            )
        }
        IconButton(
            onClick = onNavigateUp,
        ) {
            Icon(
                imageVector = Icons.Rounded.Close,
                contentDescription = "Back",
                tint = Color.White
            )
        }
    }
}

@Composable
fun ExitAlertDialog(
    onConfirm: () -> Unit,
    onCancel: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Dialog(
        onDismissRequest = onCancel,
    ) {
        Box(
            modifier = modifier
                .rotate(90f)
                .padding(24.dp)
                .background(
                    color = MaterialTheme.colorScheme.surface,
                    shape = MaterialTheme.shapes.extraLarge
                )
                .padding(horizontal = 24.dp, vertical = 16.dp)
        ) {
            Column {
                Text(
                    text = "Keluar",
                    style = MaterialTheme.typography.headlineSmall
                )
                Spacer(Modifier.height(16.dp))
                Text(
                    text = "Apakah anda yakin ingin berhenti latihan?",
                    style = MaterialTheme.typography.bodyMedium
                )
                Spacer(Modifier.height(24.dp))
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.End,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    TextButton(
                        onClick = onCancel
                    ) {
                        Text("No")
                    }
                    TextButton(
                        onClick = onConfirm
                    ) {
                        Text("Yes")
                    }
                }
            }
        }
    }
}