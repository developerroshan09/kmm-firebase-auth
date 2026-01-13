package com.kmm_firebase_auth.domain.auth

import com.kmm_firebase_auth.domain.model.User

// Swift-friendly wrapper for email/password login and Google sign-in
sealed class UserResult {
    data class Success(val user: User) : UserResult()
    data class Failure(val error: AuthError) : UserResult()
}

// Swift-friendly wrapper for logout (Unit result)
sealed class LogoutResult {
    object Success : LogoutResult()
    data class Failure(val error: AuthError) : LogoutResult()
}
