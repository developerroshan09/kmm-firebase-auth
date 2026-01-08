package com.kmm_firebase_auth.domain.usecase

import com.kmm_firebase_auth.domain.repository.AuthRepository

class LogoutUseCase(private val repo: AuthRepository) {
    suspend operator fun invoke() = repo.logout()
}