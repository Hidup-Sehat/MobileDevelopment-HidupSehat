package com.bangkit23.hidupsehat.presentation.screen.auth.common

import android.content.Context
import android.content.Intent
import android.content.IntentSender
import com.bangkit23.hidupsehat.R
import com.bangkit23.hidupsehat.presentation.model.User
import com.bangkit23.hidupsehat.presentation.screen.auth.model.SignInResult
import com.bangkit23.hidupsehat.presentation.screen.auth.model.UserData
import com.google.android.gms.auth.api.identity.BeginSignInRequest
import com.google.android.gms.auth.api.identity.BeginSignInRequest.GoogleIdTokenRequestOptions
import com.google.android.gms.auth.api.identity.SignInClient
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.tasks.await

class GoogleAuthUiClient(
    private val context: Context,
    private val onTapClient: SignInClient,
) {
    private val auth = Firebase.auth
    private val fireStore = Firebase.firestore

    suspend fun signIn(): IntentSender? {
        val result = try {
            onTapClient.beginSignIn(
                buildSignInRequest()
            ).await()
        } catch (e: Exception) {
            e.printStackTrace()
            if (e is CancellationException) throw e
            null
        }
        return result?.pendingIntent?.intentSender
    }

    suspend fun signInWithIntent(intent: Intent): SignInResult {
        val credential = onTapClient.getSignInCredentialFromIntent(intent)
        val googleIdToken = credential.googleIdToken
        val googleCredentials = GoogleAuthProvider.getCredential(googleIdToken, null)
        return try {
            val signInTask = auth.signInWithCredential(googleCredentials).await()
            val user = signInTask.user
            SignInResult(
                data = user?.run {
                    UserData(
                        userId = uid,
                        username = displayName,
                        profilePictureUrl = photoUrl?.toString(),
                        isNewUser = !isUserAvailable(uid)
                    )
                },
                errorMessage = null
            )
        } catch (e: Exception) {
            e.printStackTrace()
            if (e is CancellationException) throw e
            SignInResult(
                data = null,
                errorMessage = null
            )
        }
    }

    suspend fun signInWithEmail(email: String, password: String): SignInResult {
        return try {
            val signInTask = auth.signInWithEmailAndPassword(email, password).await()
            val user = signInTask.user
            SignInResult(
                data = user?.run {
                    UserData(
                        userId = uid,
                        username = displayName,
                        profilePictureUrl = photoUrl.toString(),
                        isNewUser = !isUserAvailable(uid)
                    )
                },
                errorMessage = null
            )
        } catch (e: Exception) {
            e.printStackTrace()
            if (e is CancellationException) throw e
            SignInResult(
                data = null,
                errorMessage = null
            )
        }
    }

    suspend fun registerWithEmail(email: String, password: String): SignInResult {
        return try {
            val registerTask = auth.createUserWithEmailAndPassword(email, password).await()
            val user = registerTask.user
            SignInResult(
                data = user?.run {
                    UserData(
                        userId = uid,
                        username = displayName,
                        profilePictureUrl = photoUrl.toString(),
                        isNewUser = !isUserAvailable(uid)
                    )
                },
                errorMessage = null
            )
        } catch (e: Exception) {
            e.printStackTrace()
            if (e is CancellationException) throw e
            SignInResult(
                data = null,
                errorMessage = null
            )
        }
    }

    suspend fun signOut() {
        try {
            onTapClient.signOut().await()
            auth.signOut()
        } catch (e: Exception) {
            e.printStackTrace()
            if (e is CancellationException) throw e
        }
    }

    fun getSignedUser(): UserData? = auth.currentUser?.run {
        UserData(
            userId = uid,
            username = displayName,
            profilePictureUrl = photoUrl?.toString(),
        )
    }

    suspend fun saveUser(user: User) {
        try {
            fireStore.collection("users")
                .document(user.userId.toString())
                .set(user)
                .await()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    private suspend fun isUserAvailable(userId: String): Boolean {
        return try {
            val snapshot = fireStore.collection("users")
                .document(userId)
                .get()
                .await()
            snapshot.exists()
        } catch (e: Exception) {
            e.printStackTrace()
            false
        }
    }

    private fun buildSignInRequest(): BeginSignInRequest {
        return BeginSignInRequest.Builder()
            .setGoogleIdTokenRequestOptions(
                GoogleIdTokenRequestOptions.builder()
                    .setSupported(true)
                    .setFilterByAuthorizedAccounts(false)
                    .setServerClientId(context.getString(R.string.web_client_id))
                    .build()
            )
            .setAutoSelectEnabled(true)
            .build()
    }
}