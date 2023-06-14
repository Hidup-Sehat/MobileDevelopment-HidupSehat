package com.bangkit23.hidupsehat.data.source.remote

import com.bangkit23.hidupsehat.data.source.remote.request.AddPointsRequest
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

    suspend fun getUserNeeds(userId: String) =
        apiService.getUserNeeds(userId)

    suspend fun getLeaderboard(type: String) =
        apiService.getLeaderboard(type)

    suspend fun getActivities() =
        apiService.getActivities()

    suspend fun addUserPoints(userId: String, points: Int) =
        apiService.addUserPoints(userId, AddPointsRequest(points))

    suspend fun getFeeds(feedRequest: FeedRequest) =
        apiService.getFeeds(feedRequest)

    suspend fun getFeedDetailById(id: String) =
        apiService.getFeedDetailById(id)
}