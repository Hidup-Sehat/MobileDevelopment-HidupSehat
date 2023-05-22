package com.bangkit23.hidupsehat.presentation.screen.preference.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

@Composable
fun ItemChoice(
    selectedId: Int,
    choiceId: Int,
    choiceTitle: String,
    choiceIcon: ImageVector,
    onSelected: (choiceId: Int) -> Unit,
    modifier: Modifier = Modifier,
) {
    OutlinedCard(
        colors = CardDefaults.outlinedCardColors(
            contentColor = if (selectedId == choiceId) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onSurface,
        ),
        border = BorderStroke(
            width = if (selectedId == choiceId) 2.dp else CardDefaults.outlinedCardBorder().width,
            color = if (selectedId == choiceId) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.outline),
        modifier = modifier
            .clickable { onSelected(choiceId) }
            .fillMaxWidth()
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Icon(
                imageVector = choiceIcon,
                contentDescription = null,
                tint = Color.Unspecified,
                modifier = Modifier
                    .padding(vertical = 16.dp)
                    .padding(start = 16.dp)
            )
            Text(
                text = choiceTitle,
                fontWeight = if (selectedId == choiceId) FontWeight.Bold else FontWeight.Light,
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier
                    .padding(16.dp)
                    .padding(vertical = 4.dp)
            )
        }
    }
}