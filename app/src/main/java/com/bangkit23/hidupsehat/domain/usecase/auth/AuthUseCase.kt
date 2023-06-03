package com.bangkit23.hidupsehat.domain.usecase.auth

import android.content.Intent
import com.bangkit23.hidupsehat.presentation.screen.auth.model.SignInResult
import com.bangkit23.hidupsehat.presentation.screen.auth.model.UserData
import com.bangkit23.hidupsehat.util.Result
import kotlinx.coroutines.flow.Flow

interface AuthUseCase {
    fun signInWithIntent(intent: Intent): Flow<Result<SignInResult>>
    fun signInWithEmail(email: String, password: String): Flow<Result<SignInResult>>
    fun registerWithEmail(name: String, email: String, password: String): Flow<Result<SignInResult>>
    fun getSignedUser(): Flow<Result<UserData?>>
    suspend fun signOut()
}