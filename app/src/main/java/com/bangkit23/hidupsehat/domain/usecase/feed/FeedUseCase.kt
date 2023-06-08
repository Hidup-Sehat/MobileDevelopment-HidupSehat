package com.bangkit23.hidupsehat.domain.usecase.feed

import com.bangkit23.hidupsehat.domain.model.feed.Feed
import com.bangkit23.hidupsehat.util.Result
import kotlinx.coroutines.flow.Flow

interface FeedUseCase {
    fun getFeeds() : Flow<Result<List<Feed>>>
}