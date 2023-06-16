package com.bangkit23.hidupsehat.presentation.screen.feeds

import com.bangkit23.hidupsehat.domain.model.feed.Feed

data class FeedState(
    val feedResult : List<Feed> = listOf()
)
