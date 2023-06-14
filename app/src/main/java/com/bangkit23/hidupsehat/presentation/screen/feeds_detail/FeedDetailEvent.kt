package com.bangkit23.hidupsehat.presentation.screen.feeds_detail

sealed class FeedDetailEvent {
    data class OnGetDetailFeed(val id: String) : FeedDetailEvent()
}
