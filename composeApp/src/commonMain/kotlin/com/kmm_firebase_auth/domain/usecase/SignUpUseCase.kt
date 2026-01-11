package com.kmm_firebase_auth.domain.usecase

import com.kmm_firebase_auth.domain.model.User
import com.kmm_firebase_auth.domain.repository.AuthRepository
class SignUpUseCase(
    private val repository: AuthRepository
) {
    suspend operator fun invoke(
        email: String,
        password: String
    ): User {
        return repository.signUp(email, password)
    }
}