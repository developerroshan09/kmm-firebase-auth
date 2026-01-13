package com.kmm_firebase_auth.domain.usecase

import com.kmm_firebase_auth.domain.auth.AuthResult
import com.kmm_firebase_auth.domain.auth.LogoutResult
import com.kmm_firebase_auth.domain.model.User
import com.kmm_firebase_auth.domain.repository.AuthRepository

class LogoutUseCase(private val repository: AuthRepository) {
    suspend fun invokeWrapper(): LogoutResult {
        val result = repository.logout()
        return when (result) {
            is AuthResult.Success -> LogoutResult.Success
            is AuthResult.Failure -> LogoutResult.Failure(result.error)
        }
    }

}
