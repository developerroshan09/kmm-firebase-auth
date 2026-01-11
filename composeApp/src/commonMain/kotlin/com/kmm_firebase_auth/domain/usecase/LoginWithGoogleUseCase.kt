package com.kmm_firebase_auth.domain.usecase

import com.kmm_firebase_auth.domain.repository.AuthRepository

class LoginWithGoogleUseCase(private val repository: AuthRepository) {
    suspend operator fun invoke(idToken: String) = repository.loginWithGoogle(idToken)
}