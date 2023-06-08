package com.bangkit23.hidupsehat.data.source.remote

import com.bangkit23.hidupsehat.data.source.remote.request.FeedRequest
import com.bangkit23.hidupsehat.data.source.remote.request.UserDetailRequest
import com.bangkit23.hidupsehat.data.source.remote.retrofit.ApiService
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RemoteDataSource @Inject constructor(
    private val apiService: ApiService,
) {

    suspend fun createUserDetail(userId: String, userDetail: UserDetailRequest) =
        apiService.createUserDetail(userId, userDetail)

    suspend fun getFeeds(feedRequest: FeedRequest) =
        apiService.getFeeds(feedRequest)
}