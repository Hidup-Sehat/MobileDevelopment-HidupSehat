package com.bangkit23.hidupsehat.data.source.remote.retrofit

import com.bangkit23.hidupsehat.data.source.remote.request.FeedRequest
import com.bangkit23.hidupsehat.data.source.remote.request.UserDetailRequest
import com.bangkit23.hidupsehat.data.source.remote.response.FeedDetailResponse
import com.bangkit23.hidupsehat.data.source.remote.response.FeedResponse
import com.bangkit23.hidupsehat.data.source.remote.response.UserDetailResponse
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

    @POST("feeds?page=1&limit=20")
    @Headers("Content-Type: application/json")
    suspend fun getFeeds(
        @Body requestBody : FeedRequest
    ) : FeedResponse

    @GET("feeds/{id}")
    suspend fun getFeedDetailById(
        @Path("id") id : String
    ) : FeedDetailResponse
}