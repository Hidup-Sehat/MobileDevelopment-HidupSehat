package com.bangkit23.hidupsehat.presentation.screen.feeds

import com.bangkit23.hidupsehat.domain.model.feed.Feed

sealed class FeedEvent {
    data class OnGetFeeds(
        val data : List<Feed>
    ) : FeedEvent()
}
