package com.bangkit23.hidupsehat.data.source.remote.retrofit

import com.bangkit23.hidupsehat.data.source.remote.request.AddFoodsRequest
import com.bangkit23.hidupsehat.data.source.remote.request.AddEmotionRequest
import com.bangkit23.hidupsehat.data.source.remote.request.AddPointsRequest
import com.bangkit23.hidupsehat.data.source.remote.request.FeedRequest
import com.bangkit23.hidupsehat.data.source.remote.request.UserDetailRequest
import com.bangkit23.hidupsehat.data.source.remote.response.ActivityResponse
import com.bangkit23.hidupsehat.data.source.remote.response.AddFoodsResponse
import com.bangkit23.hidupsehat.data.source.remote.response.AddEmotionResponse
import com.bangkit23.hidupsehat.data.source.remote.response.AddPointsResponse
import com.bangkit23.hidupsehat.data.source.remote.response.FoodsHistoryResponse
import com.bangkit23.hidupsehat.data.source.remote.response.LeaderboardResponse
import com.bangkit23.hidupsehat.data.source.remote.response.FeedDetailResponse
import com.bangkit23.hidupsehat.data.source.remote.response.FeedResponse
import com.bangkit23.hidupsehat.data.source.remote.response.UserDetailResponse
import com.bangkit23.hidupsehat.data.source.remote.response.UserNeedsResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface ApiService {

    @POST("user/{user_id}/detail")
    @Headers("Content-Type: application/json")
    suspend fun createUserDetail(
        @Path("user_id") userId: String,
        @Body requestBody: UserDetailRequest,
    ): UserDetailResponse

    @GET("user/{user_id}")
    suspend fun getUserNeeds(
        @Path("user_id") userId: String,
    ): UserNeedsResponse

    @PUT("user/{user_id}/point")
    @Headers("Content-Type: application/json")
    suspend fun addUserPoints(
        @Path("user_id") userId: String,
        @Body requestBody: AddPointsRequest,
    ): AddPointsResponse

    @GET("{leaderboard_type}")
    suspend fun getLeaderboard(
        @Path("leaderboard_type") type: String,
    ): LeaderboardResponse

    @GET("activity")
    suspend fun getActivities() : ActivityResponse

    @POST("feeds?page=1&limit=20")
    @Headers("Content-Type: application/json")
    suspend fun getFeeds(
        @Body requestBody : FeedRequest
    ) : FeedResponse

    @GET("feeds/{id}")
    suspend fun getFeedDetailById(
        @Path("id") id : String
    ) : FeedDetailResponse

    @POST("users/{user_id}/food")
    suspend fun saveFoods(
        @Path("user_id") userId: String,
        @Body requestBody: AddFoodsRequest
    ): AddFoodsResponse

    @GET("user/{user_id}/food/{date}")
    suspend fun getFoodsHistory(
        @Path("user_id") userId: String,
        @Path("date") date: String,
    ): FoodsHistoryResponse

    @POST("user/{id}/emotion")
    suspend fun addUserEmotions(
        @Path("id") id : String,
        @Body requestBody : AddEmotionRequest
    ) : AddEmotionResponse
}