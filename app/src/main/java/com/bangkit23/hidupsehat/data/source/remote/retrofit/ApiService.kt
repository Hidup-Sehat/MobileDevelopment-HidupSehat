package com.bangkit23.hidupsehat.data.source.remote.retrofit

import com.bangkit23.hidupsehat.data.source.remote.request.UserDetailRequest
import com.bangkit23.hidupsehat.data.source.remote.response.ActivityResponse
import com.bangkit23.hidupsehat.data.source.remote.response.LeaderboardResponse
import com.bangkit23.hidupsehat.data.source.remote.response.UserDetailResponse
import com.bangkit23.hidupsehat.data.source.remote.response.UserNeedsResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST
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

    @GET("leaderboard")
    suspend fun getLeaderboard(): LeaderboardResponse

    @GET("activity")
    suspend fun getActivities() : ActivityResponse
}