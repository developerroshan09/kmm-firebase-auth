package com.kmm_firebase_auth.platform

import com.kmm_firebase_auth.domain.auth.AuthResult
import com.kmm_firebase_auth.domain.auth.UserResult
import com.kmm_firebase_auth.domain.auth.LogoutResult

// Email/password login
suspend fun FirebaseAuthWrapper.loginWrapper(email: String, password: String): UserResult {
    val result = login(email, password)
    return when(result) {
        is AuthResult.Success -> UserResult.Success(result.value)
        is AuthResult.Failure -> UserResult.Failure(result.error)
    }
}

// Google login
suspend fun FirebaseAuthWrapper.loginWithGoogleWrapper(idToken: String): UserResult {
    val result = loginWithGoogle(idToken)
    return when(result) {
        is AuthResult.Success -> UserResult.Success(result.value)
        is AuthResult.Failure -> UserResult.Failure(result.error)
    }
}

// Sign-up
suspend fun FirebaseAuthWrapper.signUpWrapper(email: String, password: String): UserResult {
    val result = signUp(email, password)
    return when(result) {
        is AuthResult.Success -> UserResult.Success(result.value)
        is AuthResult.Failure -> UserResult.Failure(result.error)
    }
}

// Logout
suspend fun FirebaseAuthWrapper.logoutWrapper(): LogoutResult {
    val result = logout()
    return when(result) {
        is AuthResult.Success -> LogoutResult.Success
        is AuthResult.Failure -> LogoutResult.Failure(result.error)
    }
}
