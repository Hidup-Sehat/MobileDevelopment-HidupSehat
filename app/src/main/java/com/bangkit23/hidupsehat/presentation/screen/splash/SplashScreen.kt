package com.bangkit23.hidupsehat.presentation.screen.splash

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.bangkit23.hidupsehat.R
import kotlinx.coroutines.delay
import kotlin.time.Duration.Companion.milliseconds

@Composable
fun SplashScreen(
    onTimeOut: (Boolean) -> Unit,
    viewModel: SplashViewModel = hiltViewModel()
) {
    val isLoggedIn by viewModel.isLoggedIn
    val currentOnTimeOut by rememberUpdatedState(onTimeOut)
    LaunchedEffect(currentOnTimeOut) {
        delay(3000.milliseconds)
        onTimeOut(isLoggedIn)
    }
    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        Image(
            imageVector = ImageVector.vectorResource(R.drawable.ic_splash),
            contentDescription = null,
            modifier = Modifier
                .size(144.dp)
                .align(Alignment.Center)
        )
    }
}