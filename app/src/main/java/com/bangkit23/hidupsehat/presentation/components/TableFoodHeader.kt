package com.bangkit23.hidupsehat.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

@Composable
fun TableFoodHeader(
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier.fillMaxWidth()
            .padding(top = 16.dp)
        ,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        TableCell(
            text = "Makanan",
            weight = 0.3f,
            alignment = TextAlign.Left,
            title = true,
            modifier = Modifier.padding(start = 8.dp)
        )
        TableCell(
            text = "Takaran",
            weight = 0.3f,
            title = true
        )
        TableCell(
            text = "Jumlah",
            weight = 0.2f,
            title = true
        )
        TableCell(
            text = "Kalori",
            weight = 0.2f,
            alignment = TextAlign.Right,
            title = true,
            modifier = Modifier.padding(end = 8.dp)
        )
    }
}