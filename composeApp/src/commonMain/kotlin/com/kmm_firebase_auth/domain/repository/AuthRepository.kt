package com.kmm_firebase_auth.domain.repository

import com.kmm_firebase_auth.domain.model.AuthState
import com.kmm_firebase_auth.domain.model.User
import kotlinx.coroutines.flow.Flow

interface AuthRepository {

    suspend fun signUp(email: String, password: String): User

    suspend fun login(email: String, password: String): User

    suspend fun loginWithGoogle(idToken: String): User

    suspend fun logout(): Result<Unit>

    fun observeAuthState(): Flow<AuthState>
}
