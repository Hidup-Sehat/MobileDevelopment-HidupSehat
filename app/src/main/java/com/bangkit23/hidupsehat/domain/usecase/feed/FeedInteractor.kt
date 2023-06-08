package com.bangkit23.hidupsehat.domain.usecase.feed

import com.bangkit23.hidupsehat.domain.model.feed.Feed
import com.bangkit23.hidupsehat.domain.reporitory.FeedRepository
import com.bangkit23.hidupsehat.util.Result
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class FeedInteractor @Inject constructor(private val feedRepository: FeedRepository) : FeedUseCase {
    override fun getFeeds(): Flow<Result<List<Feed>>> {
        return feedRepository.getFeeds()
    }


}