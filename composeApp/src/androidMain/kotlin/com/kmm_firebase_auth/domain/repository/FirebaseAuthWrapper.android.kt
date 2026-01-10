package com.kmm_firebase_auth.domain.repository

import android.util.Log
import com.google.firebase.auth.FirebaseAuth
import com.kmm_firebase_auth.domain.model.AuthState
import com.kmm_firebase_auth.domain.model.User
import kotlinx.coroutines.flow.Flow

actual class FirebaseAuthWrapper actual constructor() {
    actual suspend fun signUp(email: String, password: String): User {
        TODO("Not yet implemented")
    }

    actual suspend fun login(email: String, password: String): User {
        FirebaseAuth.getInstance()
            .signInWithEmailAndPassword(email, password)
        return TODO("Provide the return value")
    }

    actual suspend fun loginWithGoogle(idToken: String): User {
        TODO("Not yet implemented")
    }

    actual suspend fun logout(): Result<Unit> {
        TODO("Not yet implemented")
    }

    actual fun getAuthState(): Flow<AuthState> {
        TODO("Not yet implemented")
    }

    actual fun signInAnonymously() {
        Log.i("FirebaseTest", "signInAnonymously() called")

        FirebaseAuth.getInstance()
            .signInAnonymously()
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    Log.i("FirebaseTest", "✅ Firebase anonymous login SUCCESS")
                } else {
                    Log.i("FirebaseTest", "❌ Firebase login FAILED", task.exception)
                }
            }
    }

    actual fun configure() {
    }
}