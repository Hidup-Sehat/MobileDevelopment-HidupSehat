package com.bangkit23.hidupsehat.presentation.screen.monitoring.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.bangkit23.hidupsehat.presentation.ui.theme.HidupSehatTheme

@Composable
fun SleepCard(
    sleepActual: Int,
    sleepNeeds: Int,
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier
            .background(
                shape = MaterialTheme.shapes.medium,
                color = Color(0xFF4FB6D7)
            )
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .padding(8.dp)
                .align(Alignment.Center)
        ) {
            Text(
                text = "Tidur",
                style = MaterialTheme.typography.titleMedium,
                color = Color.White
            )
            Spacer(Modifier.height(8.dp))
            Text(
                text = "$sleepActual dari $sleepNeeds jam",
                style = MaterialTheme.typography.bodyLarge,
                color = Color.White
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun SleepCardPreview() {
    HidupSehatTheme {
        SleepCard(
            sleepActual = 5,
            sleepNeeds = 7
        )
    }
}