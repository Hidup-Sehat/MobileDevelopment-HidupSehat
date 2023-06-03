package com.bangkit23.hidupsehat.presentation.screen.feeds

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.bangkit23.hidupsehat.presentation.screen.feeds.component.FeedItem
import com.bangkit23.hidupsehat.presentation.screen.feeds.model.Feed

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FeedScreen(modifier: Modifier = Modifier, data: List<Feed>) {
    Scaffold(
        topBar = {
            TopAppBar(title = {
                Text(text = "Feed")
            })
        },
        content = {
            FeedContent(
                modifier = Modifier.padding(it),
                data = data
            )
        }
    )
}

@Composable
fun FeedContent(
    modifier: Modifier = Modifier,
    data: List<Feed>
) {
    LazyColumn {
        items(data) {
            FeedItem(
                modifier = modifier,
                image = it.image,
                title = it.title,
                createdAt = it.createdAt,
                publishedBy = it.publishedBy,
                description = it.description
            )
        }
    }
}

@Preview
@Composable
fun FeeScreenPrev() {
    val dummyList = listOf<Feed>(
        Feed(
            1,
            image = "",
            title = "Honey-Basted Grilled Peaches With Greek Yogurt",
            createdAt = "4 days ago",
            publishedBy = "MyFitnessPal Blog",
            description = "The grill is for more than steak and burgers, fire it up for this peachy dessert"
        )
    )
    FeedScreen(data = dummyList)
}