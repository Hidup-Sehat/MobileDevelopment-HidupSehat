package com.bangkit23.hidupsehat.presentation.screen.exercise_play

import android.view.SurfaceView
import android.view.ViewGroup
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.viewinterop.AndroidView

@Composable
fun SurfaceView(
    modifier: Modifier = Modifier,
    onSurface: (SurfaceView) -> Unit,
) {
    AndroidView(
        modifier = modifier,
        factory = { context ->
            val surfaceView = SurfaceView(context).apply {
                layoutParams = ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.MATCH_PARENT
                )
            }
            onSurface(surfaceView)
            surfaceView
        }
    )
}