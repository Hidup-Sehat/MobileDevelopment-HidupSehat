package com.bangkit23.hidupsehat.data.source.remote

import com.bangkit23.hidupsehat.data.source.remote.request.AddEmotionRequest
import com.bangkit23.hidupsehat.data.source.remote.request.AddFoodsRequest
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

    suspend fun saveFoods(userId: String, addFoodsRequest: AddFoodsRequest) =
        apiService.saveFoods(userId, addFoodsRequest)

    suspend fun getFoodsHistory(userId: String) =
        apiService.getFoodsHistory(userId)

    suspend fun getFeeds(feedRequest: FeedRequest) =
        apiService.getFeeds(feedRequest)

    suspend fun getFeedDetailById(id: String) =
        apiService.getFeedDetailById(id)

    suspend fun addUserDiary(
        userId: String,
        date: String,
        lastUpdated: String,
        note: String,
        emotionSource: String,
        emotionPositive: String,
        emotionNegative: String
    ) = apiService.addUserEmotions(
        userId,
        AddEmotionRequest(date, lastUpdated, note, emotionSource, emotionPositive, emotionNegative)
    )

    suspend fun getDiaryByDate(
        userId: String,
        date: String,
    ) = apiService.getDiaryByDate(id = userId, date = date)
}