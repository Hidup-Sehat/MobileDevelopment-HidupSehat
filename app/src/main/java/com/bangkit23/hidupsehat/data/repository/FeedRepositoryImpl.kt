package com.bangkit23.hidupsehat.data.repository

import com.bangkit23.hidupsehat.data.source.firebase.FirebaseAuth
import com.bangkit23.hidupsehat.data.source.remote.RemoteDataSource
import com.bangkit23.hidupsehat.data.source.remote.request.FeedRequest
import com.bangkit23.hidupsehat.domain.model.feed.DetailFeed
import com.bangkit23.hidupsehat.domain.model.feed.Feed
import com.bangkit23.hidupsehat.domain.reporitory.FeedRepository
import com.bangkit23.hidupsehat.util.Result
import com.bangkit23.hidupsehat.util.toDomain
import com.bangkit23.hidupsehat.util.toDomainn
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn


import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class FeedRepositoryImpl @Inject constructor(private val remoteDataSource: RemoteDataSource,
private val firebaseAuth : FirebaseAuth) :
    FeedRepository {
    override fun getFeeds(): Flow<Result<List<Feed>>> = flow {
        emit(Result.Loading())
        try {
            val request = firebaseAuth.getSignedUser()?.userId
            val response = remoteDataSource.getFeeds(FeedRequest(userUid = request.toString()))
            val data = response.data.toDomainn()
            if (data.isNotEmpty()) {
                emit(Result.Success(data))
            }

        } catch (e: Exception) {
            e.printStackTrace()
            emit(Result.Error(e.message))
        }
    }.flowOn(Dispatchers.IO)

    override fun feedDetailById(id: String): Flow<Result<DetailFeed>> = flow {
        try {
            val response = remoteDataSource.getFeedDetailById(id)
            val data = response.toDomain()
            emit(Result.Success(data))
        }catch (e: Exception) {
            e.printStackTrace()
            emit(Result.Error(e.message))
        }
    }.flowOn(Dispatchers.IO)

}