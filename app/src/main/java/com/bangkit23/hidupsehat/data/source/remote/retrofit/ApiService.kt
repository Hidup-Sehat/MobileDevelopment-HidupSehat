package com.bangkit23.hidupsehat.data.source.remote.retrofit

import com.bangkit23.hidupsehat.data.source.remote.request.UserDetailRequest
import com.bangkit23.hidupsehat.data.source.remote.response.UserDetailResponse
import retrofit2.http.Body
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
}