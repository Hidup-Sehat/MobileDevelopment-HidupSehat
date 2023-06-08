package com.bangkit23.hidupsehat.presentation.screen.feeds

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.bangkit23.hidupsehat.presentation.screen.feeds.component.FeedItem
import com.bangkit23.hidupsehat.presentation.screen.feeds.model.Feed

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FeedScreen(
    modifier: Modifier = Modifier,
    data: List<Feed>, onClick: () -> Unit,
    viewModel: FeedViewModel = hiltViewModel()
    ) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    LaunchedEffect(key1 = state.feedResult){}
    Scaffold(
        topBar = {
            TopAppBar(title = {
                Text(text = "Feed")
            })
        },
        content = {
            FeedContent(
                modifier = Modifier.padding(it),
                data = state.feedResult,
                onClick = onClick,
            )
        }
    )
}

@Composable
fun FeedContent(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
    data: List<com.bangkit23.hidupsehat.domain.model.feed.Feed>
) {
    LazyColumn {
        items(data) {
            FeedItem(
                modifier = modifier,
                image = it.imgUrl!!,
                title = it.title!!,
                createdAt = it.createdAt!!,
                publishedBy = it.author!!,
                description = it.summary!!,
                onClick = onClick,
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
    FeedScreen(data = dummyList, onClick = {})
}