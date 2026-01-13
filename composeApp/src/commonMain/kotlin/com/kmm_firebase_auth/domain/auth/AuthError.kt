package com.kmm_firebase_auth.domain.auth

sealed class AuthError {
    abstract val code: AuthErrorCode
    abstract val message: String?

    data class Firebase(
        override val code: AuthErrorCode,
        override val message: String? = null,
    ) : AuthError()
}