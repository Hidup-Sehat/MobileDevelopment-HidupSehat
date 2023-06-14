package com.bangkit23.hidupsehat.data.repository

import com.bangkit23.hidupsehat.data.source.firebase.FirebaseAuth
import com.bangkit23.hidupsehat.data.source.remote.RemoteDataSource
import com.bangkit23.hidupsehat.domain.reporitory.MonitoringRepository
import com.bangkit23.hidupsehat.util.Result
import com.bangkit23.hidupsehat.util.toDomain
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MonitoringRepositoryImpl @Inject constructor(
    private val firebaseAuth: FirebaseAuth,
    private val remoteDataSource: RemoteDataSource,
) : MonitoringRepository {

    override fun getFoodsHistory(date: String) = flow {
        emit(Result.Loading())
        try {
            val userId = firebaseAuth.getSignedUser()?.userId
            val response = remoteDataSource.getFoodsHistory(userId.toString(), date)
            val result = response.food.toDomain()
            emit(Result.Success(result))
        } catch (e: Exception) {
            emit(Result.Error(e.message))
        }
    }.flowOn(Dispatchers.IO)
}