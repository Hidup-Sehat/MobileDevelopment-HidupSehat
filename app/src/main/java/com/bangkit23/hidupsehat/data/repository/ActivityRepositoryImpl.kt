package com.bangkit23.hidupsehat.data.repository

import com.bangkit23.hidupsehat.data.source.remote.RemoteDataSource
import com.bangkit23.hidupsehat.data.source.remote.response.ActivityResponseItem
import com.bangkit23.hidupsehat.domain.reporitory.ActivityRepository
import com.bangkit23.hidupsehat.util.Result
import com.bangkit23.hidupsehat.util.toDomain
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ActivityRepositoryImpl @Inject constructor(
    private val remoteDataSource: RemoteDataSource,
) : ActivityRepository {

    override fun getYogaActivities() = flow {
        emit(Result.Loading())
        try {
            val response = remoteDataSource.getActivities()
            val result = response.activity.map(ActivityResponseItem::toDomain)
                .filter { it.type == "Yoga" }
            emit(Result.Success(result))
        } catch (e: Exception) {
            emit(Result.Error(e.message))
        }
    }.flowOn(Dispatchers.IO)

    override fun getWorkoutActivities() = flow {
        emit(Result.Loading())
        try {
            val response = remoteDataSource.getActivities()
            val result = response.activity.map(ActivityResponseItem::toDomain)
                .filter { it.type == "Workout" }
            emit(Result.Success(result))
        } catch (e: Exception) {
            emit(Result.Error(e.message))
        }
    }.flowOn(Dispatchers.IO)
}