package com.bangkit23.hidupsehat.presentation.screen.mental_health.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.bangkit23.hidupsehat.R
import com.bangkit23.hidupsehat.domain.model.feed.Feed

@Composable
fun ListItemFeed(
    headerText: String,
    data: List<Feed>,
    onItemClicked: (Feed) -> Unit,
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
                CardFeed(
                    title = it.title ?: "",
                    image = it.imgUrl ?: "",
                    onClicked = { onItemClicked(it) }
                )
            }
        }
    }
}

@Composable
fun CardFeed(
    title: String,
    image: String,
    onClicked: () -> Unit,
    modifier: Modifier = Modifier,
) {
    ElevatedCard(
        modifier = modifier
            .width(160.dp)
            .height(200.dp)
            .clickable { onClicked() }
    ) {
        Text(
            modifier = Modifier
                .padding(horizontal = 16.dp)
                .padding(top = 14.dp), text = title,
            style = TextStyle(
                fontSize = 14.sp, fontWeight = FontWeight.Medium
            )
        )
        Spacer(Modifier.height(8.dp))
        AsyncImage(
            modifier = Modifier.fillMaxSize(),
            model = image, contentDescription = null,
            contentScale = ContentScale.Crop,
            placeholder = painterResource(id = R.drawable.place_holder)
        )
    }
}