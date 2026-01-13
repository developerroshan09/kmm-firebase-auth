package com.kmm_firebase_auth.domain.auth

sealed class AuthResult<out T> {
    data class Success<T>(val value: T) : AuthResult<T>()
    data class Failure(val error: AuthError) : AuthResult<Nothing>()
}