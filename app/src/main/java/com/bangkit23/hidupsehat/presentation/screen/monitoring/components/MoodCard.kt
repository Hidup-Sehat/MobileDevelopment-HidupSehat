package com.bangkit23.hidupsehat.presentation.screen.monitoring.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.bangkit23.hidupsehat.presentation.ui.theme.HidupSehatTheme

@Composable
fun MoodCard(
    icon: String,
    positiveEmotion: String,
    negativeEmotion: String,
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier
            .background(
                shape = MaterialTheme.shapes.medium,
                brush = Brush.linearGradient(
                    colors = listOf(
                        MaterialTheme.colorScheme.secondaryContainer,
                        MaterialTheme.colorScheme.primary
                    )
                )
            )
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .padding(8.dp)
                .align(Alignment.Center)
        ) {
            Text(
                text = icon,
                fontSize = 40.sp
            )
            Row {
                Text(
                    text = positiveEmotion,
                    color = Color.White
                )
                Spacer(Modifier.width(16.dp))
                Text(
                    text = negativeEmotion,
                    color = Color.White
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MoodCardPreview() {
    HidupSehatTheme {
        MoodCard(
            icon = "\uD83D\uDE42",
            positiveEmotion = "Senang",
            negativeEmotion = "Cemas",
        )
    }
}