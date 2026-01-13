package com.kmm_firebase_auth.domain.usecase

import com.kmm_firebase_auth.domain.auth.AuthResult
import com.kmm_firebase_auth.domain.auth.UserResult
import com.kmm_firebase_auth.domain.model.User
import com.kmm_firebase_auth.domain.repository.AuthRepository
class SignUpUseCase(
    private val repository: AuthRepository
) {
    suspend fun invokeWrapper(email: String, password: String): UserResult {
        val result = repository.signUp(email, password)
        return when (result) {
            is AuthResult.Success -> UserResult.Success(result.value)
            is AuthResult.Failure -> UserResult.Failure(result.error)
        }
    }
}
