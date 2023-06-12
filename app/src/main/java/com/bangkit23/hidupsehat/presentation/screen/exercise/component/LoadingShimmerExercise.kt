package com.bangkit23.hidupsehat.presentation.screen.exercise.component

import androidx.compose.foundation.background
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.bangkit23.hidupsehat.presentation.components.shimmerBrush

@Composable
fun LoadingShimmerExercise(
    modifier: Modifier = Modifier
) {
    Row(
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        modifier = modifier.horizontalScroll(rememberScrollState())
    ) {
        AsyncImage(
            model = null,
            contentDescription = null,
            modifier = Modifier
                .width(160.dp)
                .height(200.dp)
                .background(brush = shimmerBrush(), shape = MaterialTheme.shapes.medium)
        )
        AsyncImage(
            model = null,
            contentDescription = null,
            modifier = Modifier
                .width(160.dp)
                .height(200.dp)
                .background(brush = shimmerBrush(), shape = MaterialTheme.shapes.medium)
        )
        AsyncImage(
            model = null,
            contentDescription = null,
            modifier = Modifier
                .width(160.dp)
                .height(200.dp)
                .background(brush = shimmerBrush(), shape = MaterialTheme.shapes.medium)
        )
    }
}