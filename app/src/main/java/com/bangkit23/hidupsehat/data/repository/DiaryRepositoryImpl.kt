package com.bangkit23.hidupsehat.data.repository

import com.bangkit23.hidupsehat.data.source.firebase.FirebaseAuth
import com.bangkit23.hidupsehat.data.source.remote.RemoteDataSource
import com.bangkit23.hidupsehat.domain.model.diary.Diary
import com.bangkit23.hidupsehat.domain.reporitory.DiaryRepository
import com.bangkit23.hidupsehat.util.Result
import com.bangkit23.hidupsehat.util.toDomain
import kotlinx.coroutines.Dispatchers

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DiaryRepositoryImpl @Inject constructor(
    private val firebaseAuth: FirebaseAuth,
    private val remoteDataSource: RemoteDataSource
) : DiaryRepository {
    override fun addDiary(
        date: String,
        lastUpdated: String,
        note: String,
        emotionSource: String,
        emotionPositive: String,
        emotionNegative: String
    ): Flow<Result<Diary>> = flow {
        emit(Result.Loading())
        try {
            val userId = firebaseAuth.getSignedUser()?.userId
            val response = remoteDataSource.addUserDiary(
                userId.toString(),
                date,
                lastUpdated,
                note,
                emotionSource,
                emotionPositive,
                emotionNegative
            )
            val data = response.data?.toDomain()
            if (data != null && data.id.isNotEmpty()) {
                emit(Result.Success(data))
            } else {
                emit(Result.Error("No data response"))
            }
        } catch (e: Exception) {
            e.printStackTrace()
            emit(Result.Error(e.localizedMessage))
        }
    }.flowOn(Dispatchers.IO)

    override fun getDiaryByDate(date: String): Flow<Result<Diary>> = flow {
        emit(Result.Loading())
        try {
            val userId = firebaseAuth.getSignedUser()?.userId
            val response = remoteDataSource.getDiaryByDate(userId.toString(), date)
            val data = response.data.toDomain()
            if (data.id.isNotEmpty()){
                emit(Result.Success(data))
            }
        }catch (e : Exception){
            e.printStackTrace()
            emit(Result.Error(e.message))
        }
    }.flowOn(Dispatchers.IO)
}