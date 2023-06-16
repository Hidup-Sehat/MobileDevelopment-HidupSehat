package com.bangkit23.hidupsehat.data.repository

import android.content.Intent
import com.bangkit23.hidupsehat.data.source.firebase.FirebaseAuth
import com.bangkit23.hidupsehat.domain.reporitory.AuthRepository
import com.bangkit23.hidupsehat.util.Result
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AuthRepositoryImpl @Inject constructor(
    private val firebaseAuth: FirebaseAuth,
) : AuthRepository {

    override fun signInWithIntent(intent: Intent) = flow {
        emit(Result.Loading())
        try {
            val result = firebaseAuth.signInWithIntent(intent)
            emit(Result.Success(result))
        } catch (e: Exception) {
            e.printStackTrace()
            emit(Result.Error(e.message))
        }
    }.flowOn(Dispatchers.IO)

    override fun signInWithEmail(email: String, password: String) = flow {
        emit(Result.Loading())
        try {
            val result = firebaseAuth.signInWithEmail(email, password)
            emit(Result.Success(result))
        } catch (e: Exception) {
            e.printStackTrace()
            emit(Result.Error(e.message))
        }
    }.flowOn(Dispatchers.IO)

    override fun registerWithEmail(name: String, email: String, password: String) = flow {
        emit(Result.Loading())
        try {
            val result = firebaseAuth.registerWithEmail(name, email, password)
            emit(Result.Success(result))
        } catch (e: Exception) {
            e.printStackTrace()
            emit(Result.Error(e.message))
        }
    }.flowOn(Dispatchers.IO)

    override fun getSignedUser() = flow {
        val user = firebaseAuth.getSignedUser()
        emit(user)
    }.flowOn(Dispatchers.IO)

    override suspend fun signOut() {
        firebaseAuth.signOut()
    }
}