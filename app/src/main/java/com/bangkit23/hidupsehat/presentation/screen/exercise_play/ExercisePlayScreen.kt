package com.bangkit23.hidupsehat.presentation.screen.exercise_play

import android.Manifest
import android.util.Log
import android.view.SurfaceView
import androidx.camera.core.CameraSelector
import androidx.camera.core.ImageAnalysis
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.AsyncImage
import com.bangkit23.hidupsehat.presentation.screen.exercise.model.Exercise
import com.bangkit23.hidupsehat.presentation.screen.exercise_play.common.MoveNet
import com.bangkit23.hidupsehat.presentation.screen.exercise_play.common.PoseEstimationAnalyzer
import com.bangkit23.hidupsehat.presentation.screen.exercise_play.components.PoseScore
import com.bangkit23.hidupsehat.presentation.screen.exercise_play.model.PersonBodyAngle
import com.bangkit23.hidupsehat.presentation.screen.exercise_play.model.ml.Device
import com.bangkit23.hidupsehat.util.executor
import com.bangkit23.hidupsehat.util.getCameraProvider
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.PermissionRequired
import com.google.accompanist.permissions.rememberPermissionState
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import kotlinx.coroutines.delay

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

    LaunchedEffect(key1 = state.currentPosePosition) {
        delay(20000L)
        viewModel.onEvent(ExercisePlayEvent.OnTimerEnd(state.currentPosePosition))
    }
    LaunchedEffect(key1 = state.isExerciseDone) {
        if (state.isExerciseDone) {
            onNavigateUp()
        }
    }

    ExercisePlayContent(
        score = state.poseScore,
        poseImage = exercise.poses[state.currentPosePosition].image,
        onAngleChange = {
            viewModel.onEvent(ExercisePlayEvent.SetScore(it))
        },
        onNoPerson = {
            viewModel.onEvent(ExercisePlayEvent.ResetScore)
        },
    )
}

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun ExercisePlayContent(
    score: Double,
    poseImage: String,
    onAngleChange: (PersonBodyAngle) -> Unit,
    onNoPerson: () -> Unit,
    modifier: Modifier = Modifier,
    cameraSelector: CameraSelector = CameraSelector.DEFAULT_FRONT_CAMERA,
) {
    val context = LocalContext.current
    val systemUiController = rememberSystemUiController()
    val permissionState = rememberPermissionState(
        Manifest.permission.CAMERA
    )
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