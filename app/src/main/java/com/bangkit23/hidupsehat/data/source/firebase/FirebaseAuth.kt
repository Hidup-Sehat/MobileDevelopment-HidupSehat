package com.bangkit23.hidupsehat.data.source.firebase

import android.content.Intent
import com.bangkit23.hidupsehat.presentation.screen.auth.model.SignInResult
import com.bangkit23.hidupsehat.presentation.screen.auth.model.UserData

interface FirebaseAuth {
    suspend fun signInWithIntent(intent: Intent): SignInResult
    suspend fun signInWithEmail(email: String, password: String): SignInResult
    suspend fun registerWithEmail(name: String, email: String, password: String): SignInResult
    suspend fun signOut()
    fun getSignedUser(): UserData?
}