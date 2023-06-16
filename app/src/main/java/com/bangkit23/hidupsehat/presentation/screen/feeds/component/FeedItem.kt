package com.bangkit23.hidupsehat.presentation.screen.feeds.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FeedItem(
    modifier: Modifier = Modifier,
    image: String,
    title: String,
    createdAt: String,
    publishedBy: String,
    description: String,
    onClick : (String) -> Unit,
    id: String,
) {
    Card(
        modifier = Modifier
            .fillMaxWidth().padding(horizontal = 16.dp).padding(bottom = 4.dp),
        shape = RoundedCornerShape(16.dp),
        onClick = {
            onClick(id)
        }
    ) {
        AsyncImage(
            modifier = Modifier.fillMaxWidth().height(200.dp),
            model = image, contentDescription = null,
            contentScale = ContentScale.Crop
            )
        Column (
            modifier = Modifier.padding(horizontal = 8.dp, vertical = 16.dp)
                ) {
            Text(text = title, style = MaterialTheme.typography.titleMedium)
            Row(modifier = Modifier.padding(vertical = 4.dp)) {
                Text(text = createdAt, style = MaterialTheme.typography.bodyMedium)
                Spacer(modifier = Modifier.width(8.dp))
                Text(text = publishedBy, style = MaterialTheme.typography.bodyMedium)
            }
            Text(text = description, style = MaterialTheme.typography.bodyMedium, maxLines = 3)
        }

    }
}

@Preview
@Composable
fun FeedItemPrev() {
    FeedItem(image = "", title = "Honey-Basted Grilled Peaches With Greek Yogurt", createdAt = "4 days ago", publishedBy = "MyFitnessPal Blog", description = "The grill is for more than steak and burgers, fire it up for this peachy dessert", id = "", onClick = {})
}