package com.bangkit23.hidupsehat.domain.usecase.auth

import android.content.Intent
import com.bangkit23.hidupsehat.domain.reporitory.AuthRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AuthInteractor @Inject constructor(
    private val authRepository: AuthRepository,
) : AuthUseCase {

    override fun signInWithIntent(intent: Intent) =
        authRepository.signInWithIntent(intent)

    override fun signInWithEmail(email: String, password: String) =
        authRepository.signInWithEmail(email, password)

    override fun registerWithEmail(name: String, email: String, password: String) =
        authRepository.registerWithEmail(name, email, password)

    override fun getSignedUser() = authRepository.getSignedUser()

    override suspend fun signOut() = authRepository.signOut()
}