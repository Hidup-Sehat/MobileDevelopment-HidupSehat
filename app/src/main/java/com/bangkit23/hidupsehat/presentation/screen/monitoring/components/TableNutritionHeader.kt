package com.bangkit23.hidupsehat.presentation.screen.monitoring.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.bangkit23.hidupsehat.presentation.components.TableCell

@Composable
fun TableNutritionHeader(
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        TableCell(
            text = "Nutrisi",
            weight = 0.4f,
            alignment = TextAlign.Left,
            title = true,
            modifier = Modifier.padding(start = 8.dp)
        )
        TableCell(
            text = "Total",
            weight = 0.2f,
            title = true
        )
        TableCell(
            text = "Target",
            weight = 0.2f,
            title = true
        )
        TableCell(
            text = "Sisa",
            weight = 0.2f,
            alignment = TextAlign.Right,
            title = true,
            modifier = Modifier.padding(end = 8.dp)
        )
    }
}