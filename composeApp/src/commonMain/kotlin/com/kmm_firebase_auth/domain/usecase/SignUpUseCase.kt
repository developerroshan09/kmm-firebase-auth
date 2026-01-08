package com.kmm_firebase_auth.domain.usecase

import com.kmm_firebase_auth.domain.model.User
import com.kmm_firebase_auth.domain.repository.AuthRepository

class SignUpUseCase(private val repo: AuthRepository) {
    suspend operator fun invoke(email: String, password: String) = repo.signUp(email, password)
}