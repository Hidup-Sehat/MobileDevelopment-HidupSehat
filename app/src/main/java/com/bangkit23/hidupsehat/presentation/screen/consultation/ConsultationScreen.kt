package com.bangkit23.hidupsehat.presentation.screen.consultation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.bangkit23.hidupsehat.presentation.components.UnderDevelopmentInfo

@Composable
fun ConsultationScreen() {
    ConsultationContent()
}

@Composable
fun ConsultationContent(
    modifier: Modifier = Modifier,
) {
    Box(modifier = modifier.fillMaxSize()) {
        UnderDevelopmentInfo(
            modifier = Modifier
                .align(Alignment.Center)
        )
    }
}