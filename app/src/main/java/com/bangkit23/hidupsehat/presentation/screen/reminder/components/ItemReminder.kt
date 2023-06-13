package com.bangkit23.hidupsehat.presentation.screen.reminder.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.bangkit23.hidupsehat.presentation.ui.theme.HidupSehatTheme

@Composable
fun ItemReminder(
    title: String,
    time: String,
    isActive: Boolean,
    onItemClicked: () -> Unit,
    onSwitchChange: (Boolean) -> Unit,
    modifier: Modifier = Modifier,
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .clickable {
                onItemClicked()
            }
            .padding(horizontal = 16.dp)
    ) {
        Column(
            modifier = Modifier.weight(1f)
                .padding(vertical = 8.dp)
        ) {
            Text(
                text = time,
                style = MaterialTheme.typography.headlineLarge
            )
            Text(
                text = title,
                style = MaterialTheme.typography.labelMedium
            )
        }
        Switch(
            checked = isActive,
            onCheckedChange = onSwitchChange
        )
    }
}

@Preview(showBackground = true)
@Composable
fun ItemReminderPreview() {
    HidupSehatTheme {
        ItemReminder(
            title = "Sarapan",
            time = "07.00",
            isActive = false,
            onItemClicked = {},
            onSwitchChange = {},
        )
    }
}