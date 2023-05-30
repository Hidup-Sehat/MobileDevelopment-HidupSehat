package com.bangkit23.hidupsehat.presentation.components

import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

@Composable
fun RowScope.TableCell(
    text: String,
    weight: Float,
    modifier: Modifier = Modifier,
    alignment: TextAlign = TextAlign.Center,
    title: Boolean = false,
) {
    Text(
        text = text,
        modifier = modifier
            .weight(weight)
            .padding(8.dp),
        style = if (title) MaterialTheme.typography.titleMedium else MaterialTheme.typography.bodyMedium,
        textAlign = alignment,
    )
}