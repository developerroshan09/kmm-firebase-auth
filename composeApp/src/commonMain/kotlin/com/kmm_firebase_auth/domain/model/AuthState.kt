package com.kmm_firebase_auth.domain.model

sealed class AuthState {
    object LoggedOut : AuthState()
    data class LoggedIn(val user: User) : AuthState()
}