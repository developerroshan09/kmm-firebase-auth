package com.kmm_firebase_auth.domain.repository

import com.kmm_firebase_auth.domain.model.User
import kotlinx.coroutines.flow.Flow
/**
 * Defines the interface for all authentication-related data operations.
 * This is an abstraction in the domain layer, implemented by platform-specific
 * data sources in the data layer (e.g., iOSAuthRepository in iosMain).
 */
interface AuthRepository {
    /**
     * Registers a new user with email and password.
     */
    suspend fun signUp(email: String, password: String): User

    /**
     * Authenticates an existing user with email and password.
     */
    suspend fun login(email: String, password: String): User

    /**
     * Logs out the current user.
     */
    suspend fun logout()

    /**
     * Observes the authentication state of the user.
     * Emits null if logged out, or a User object if logged in.
     */
    fun getAuthState(): Flow<User?>
}