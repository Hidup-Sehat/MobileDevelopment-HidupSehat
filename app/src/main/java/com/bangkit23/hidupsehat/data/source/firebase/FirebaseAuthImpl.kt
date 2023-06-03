package com.bangkit23.hidupsehat.data.source.firebase

import android.content.Intent
import com.bangkit23.hidupsehat.presentation.screen.auth.model.SignInResult
import com.bangkit23.hidupsehat.presentation.screen.auth.model.UserData
import com.google.android.gms.auth.api.identity.SignInClient
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.tasks.await
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class FirebaseAuthImpl @Inject constructor(
    private val onTapClient: SignInClient,
) : FirebaseAuth {

    private val auth = Firebase.auth
    private val fireStore = Firebase.firestore

    override suspend fun signInWithIntent(intent: Intent): SignInResult {
        val credential = onTapClient.getSignInCredentialFromIntent(intent)
        val googleIdToken = credential.googleIdToken
        val googleCredentials = GoogleAuthProvider.getCredential(googleIdToken, null)
        val signInTask = auth.signInWithCredential(googleCredentials).await()
        val user = signInTask.user
        return SignInResult(
            data = user?.run {
                UserData(
                    userId = uid,
                    username = displayName,
                    profilePictureUrl = photoUrl?.toString(),
                    isNewUser = !isUserAlreadyExists(uid)
                )
            },
            errorMessage = null
        )
    }

    override suspend fun signInWithEmail(email: String, password: String): SignInResult {
        val signInTask = auth.signInWithEmailAndPassword(email, password).await()
        val user = signInTask.user
        return SignInResult(
            data = user?.run {
                UserData(
                    userId = uid,
                    username = displayName,
                    profilePictureUrl = photoUrl.toString(),
                    isNewUser = !isUserAlreadyExists(uid)
                )
            },
            errorMessage = null
        )
    }

    override suspend fun registerWithEmail(name: String, email: String, password: String): SignInResult {
        val registerTask = auth.createUserWithEmailAndPassword(email, password).await()
        val user = registerTask.user
        return SignInResult(
            data = user?.run {
                UserData(
                    userId = uid,
                    username = displayName ?: name,
                    profilePictureUrl = photoUrl.toString(),
                    isNewUser = !isUserAlreadyExists(uid)
                )
            },
            errorMessage = null
        )
    }

    override suspend fun signOut() {
        onTapClient.signOut().await()
        auth.signOut()
    }

    override fun getSignedUser(): UserData? = auth.currentUser?.run {
        UserData(
            userId = uid,
            username = displayName,
            profilePictureUrl = photoUrl?.toString(),
        )
    }

    private suspend fun isUserAlreadyExists(userId: String): Boolean {
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
}