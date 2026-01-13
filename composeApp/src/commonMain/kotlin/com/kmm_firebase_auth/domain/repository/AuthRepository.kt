package com.kmm_firebase_auth.domain.repository

import com.kmm_firebase_auth.domain.auth.AuthResult
import com.kmm_firebase_auth.domain.model.AuthState
import com.kmm_firebase_auth.domain.model.User
import kotlinx.coroutines.flow.Flow

interface AuthRepository {

    suspend fun signUp(email: String, password: String): AuthResult<User>

    suspend fun login(email: String, password: String): AuthResult<User>

    suspend fun loginWithGoogle(idToken: String): AuthResult<User>

    suspend fun logout(): AuthResult<Unit>

    fun observeAuthState(): Flow<AuthState>
}
