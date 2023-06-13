package com.bangkit23.hidupsehat.presentation.screen.monitoring.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Divider
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.bangkit23.hidupsehat.presentation.components.TableCell
import com.bangkit23.hidupsehat.presentation.ui.theme.HidupSehatTheme

@Composable
fun ItemNutritionHistory(
    nutritionName: String,
    total: Int,
    target: Int,
    remain: Int,
    modifier: Modifier = Modifier,
) {
    val progress by remember { mutableStateOf(total.toFloat() / target.toFloat() * 100f) }

    Column(
        modifier = modifier
    ) {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = modifier.fillMaxWidth()
        ) {
            TableCell(
                text = nutritionName,
                weight = 0.4f,
                alignment = TextAlign.Left,
                modifier = Modifier.padding(start = 8.dp)
            )
            TableCell(
                text = "$total",
                weight = 0.2f,
            )
            TableCell(
                text = "$target",
                weight = 0.2f,
            )
            TableCell(
                text = "${remain}g",
                weight = 0.2f,
                alignment = TextAlign.Right,
                modifier = Modifier.padding(end = 8.dp)
            )
        }
        Spacer(Modifier.height(8.dp))
        LinearProgressIndicator(
            progress = progress / 100f,
            modifier = Modifier.fillMaxWidth()
                .padding(horizontal = 16.dp)
        )
        Spacer(Modifier.height(8.dp))
        Divider(Modifier.padding(horizontal = 16.dp))
    }
}

@Preview(showBackground = true)
@Composable
fun ItemNutritionHistoryPreview() {
    HidupSehatTheme {
        ItemNutritionHistory(
            nutritionName = "Pisang",
            120,
            300,
            180
        )
    }
}