package com.kmm_firebase_auth.domain.usecase

import com.kmm_firebase_auth.domain.repository.AuthRepository

class LoginUseCase(private val repo: AuthRepository) {
    suspend operator fun invoke(email: String, password: String) = repo.login(email, password)
}