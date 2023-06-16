package com.bangkit23.hidupsehat.presentation.screen.mental_health.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.bangkit23.hidupsehat.presentation.screen.mental_health.model.Consultation

@Composable
fun ListItemConsultation(
    headerText: String,
    data: List<Consultation>,
    onItemClicked: (Consultation) -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(modifier = modifier) {
        Text(
            modifier = Modifier.padding(start = 16.dp),
            text = headerText,
            style = MaterialTheme.typography.titleMedium
        )
        LazyRow(
            contentPadding = PaddingValues(16.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(data) {
                ItemConsultation(
                    drName = it.drName,
                    image = it.image,
                    price = it.price,
                    onClicked = { onItemClicked(it) }
                )
            }
        }
    }
}