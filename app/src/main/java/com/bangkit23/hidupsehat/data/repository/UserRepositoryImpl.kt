package com.bangkit23.hidupsehat.data.repository

import com.bangkit23.hidupsehat.data.source.firebase.FirebaseAuth
import com.bangkit23.hidupsehat.data.source.remote.RemoteDataSource
import com.bangkit23.hidupsehat.domain.model.user.UserDetailRequestDto
import com.bangkit23.hidupsehat.domain.reporitory.UserRepository
import com.bangkit23.hidupsehat.util.Result
import com.bangkit23.hidupsehat.util.toDomain
import com.bangkit23.hidupsehat.util.toRemote
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserRepositoryImpl @Inject constructor(
    private val firebaseAuth: FirebaseAuth,
    private val remoteDataSource: RemoteDataSource,
) : UserRepository {

    override fun createUserDetail(userDetailRequestDto: UserDetailRequestDto) = flow {
        emit(Result.Loading())
        try {
            val userId = firebaseAuth.getSignedUser()?.userId
            val response = remoteDataSource.createUserDetail(userId.toString(), userDetailRequestDto.toRemote())
            val data = response.data?.toDomain()
            if (data != null) {
                emit(Result.Success(data))
            } else {
                emit(Result.Error("No Data Response"))
            }
        } catch (e: Exception) {
            e.printStackTrace()
            emit(Result.Error(e.message))
        }
    }.flowOn(Dispatchers.IO)
}