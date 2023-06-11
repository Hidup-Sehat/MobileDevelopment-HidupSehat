package com.bangkit23.hidupsehat.domain.reporitory

import com.bangkit23.hidupsehat.domain.model.feed.DetailFeed
import com.bangkit23.hidupsehat.domain.model.feed.Feed
import com.bangkit23.hidupsehat.util.Result
import kotlinx.coroutines.flow.Flow

interface FeedRepository {
    fun getFeeds() : Flow<Result<List<Feed>>>
    fun feedDetailById(id : String) : Flow<Result<DetailFeed>>
}