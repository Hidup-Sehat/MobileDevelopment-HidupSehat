package com.bangkit23.hidupsehat.presentation.screen.monitoring.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.bangkit23.hidupsehat.presentation.ui.theme.HidupSehatTheme

@Composable
fun ItemFoodHistory(
    foodName: String,
    count: Int,
    portionSize: String,
    energyKKal: Double,
    modifier: Modifier = Modifier,
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier.fillMaxWidth()
            .clickable {  }
    ) {
        Column(
            modifier = Modifier.weight(1f)
                .padding(16.dp)
        ) {
            Text(
                text = foodName,
                style = MaterialTheme.typography.bodyLarge
            )
            Text(
                text = "$count x $portionSize",
                style = MaterialTheme.typography.bodyMedium
            )
        }
        Column(
            modifier = Modifier.padding(end = 16.dp),
            horizontalAlignment = Alignment.End
        ) {
            Text(
                text = "$energyKKal",
                style = MaterialTheme.typography.bodyLarge
            )
            Text(
                text = "kal",
                textAlign = TextAlign.End,
                style = MaterialTheme.typography.bodyMedium
            )
        }
    }
    Divider(Modifier.padding(horizontal = 16.dp))
}

@Preview(showBackground = true)
@Composable
fun ItemFoodHistoryPreview() {
    HidupSehatTheme {
        ItemFoodHistory(
            foodName = "Pisang",
            count = 3,
            portionSize = "100 gram",
            energyKKal = 293.0,
        )
    }
}

