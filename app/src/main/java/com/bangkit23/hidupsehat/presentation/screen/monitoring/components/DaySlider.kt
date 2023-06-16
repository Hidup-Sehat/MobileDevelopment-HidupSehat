package com.bangkit23.hidupsehat.presentation.screen.monitoring.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBackIos
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.tooling.preview.Preview
import com.bangkit23.hidupsehat.presentation.ui.theme.HidupSehatTheme

@Composable
fun DaySlider(
    day: String,
    onPreviousDayClick: () -> Unit,
    onNextDayClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = modifier
            .fillMaxWidth()
    ) {
        IconButton(
            onClick = onPreviousDayClick,
            modifier = Modifier
        ) {
            Icon(
                imageVector = Icons.Rounded.ArrowBackIos,
                contentDescription = "Previous Day"
            )
        }
        Text(
            text = day,
            style = MaterialTheme.typography.titleMedium
        )
        IconButton(
            onClick = onNextDayClick
        ) {
            Icon(
                imageVector = Icons.Rounded.ArrowBackIos,
                contentDescription = "Next Day",
                modifier = Modifier.rotate(180f)
            )
        }
    }
    Divider()
}

@Preview(showBackground = true)
@Composable
fun DaySliderPreview() {
    HidupSehatTheme {
        DaySlider(
            day = "Hari Ini",
            onNextDayClick = {},
            onPreviousDayClick = {},
        )
    }
}