package com.bangkit23.hidupsehat.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun CardFoodInformation(
    modifier: Modifier = Modifier,
    name: String,
    unit: String,
    color: Color
) {
    Card(
        modifier = modifier.width(155.dp),
        colors = CardDefaults.cardColors(containerColor = color, contentColor = Color.White)
    ) {
        Column(
            modifier = Modifier.padding(12.dp),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Text(text = name, style = MaterialTheme.typography.displaySmall.copy(fontSize = 14.sp))
            Text(
                modifier = Modifier.fillMaxWidth(),
                text = unit,
                textAlign = TextAlign.End,
                style = MaterialTheme.typography.displayMedium.copy(fontSize = 32.sp, fontWeight = FontWeight.Bold)
            )
        }
    }
}

@Preview
@Composable
fun CardFoodInformationPrev() {
    CardFoodInformation(name = "Karbohidrat", unit = "0.0g", color = Color.Green)
}