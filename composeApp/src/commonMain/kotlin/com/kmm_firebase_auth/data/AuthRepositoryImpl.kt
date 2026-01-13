package com.kmm_firebase_auth.data

import com.kmm_firebase_auth.domain.model.AuthState
import com.kmm_firebase_auth.domain.model.User
import com.kmm_firebase_auth.domain.repository.AuthRepository
import com.kmm_firebase_auth.platform.FirebaseAuthWrapper
import kotlinx.coroutines.flow.Flow

class AuthRepositoryImpl(
    private val authWrapper: FirebaseAuthWrapper
) : AuthRepository {

    override suspend fun signUp(email: String, password: String) =
        authWrapper.signUp(email, password)

    override suspend fun login(email: String, password: String) =
        authWrapper.login(email, password)

    override suspend fun loginWithGoogle(idToken: String) =
        authWrapper.loginWithGoogle(idToken)

    override suspend fun logout() =
        authWrapper.logout()

    override fun observeAuthState() =
        authWrapper.getAuthState()
}
