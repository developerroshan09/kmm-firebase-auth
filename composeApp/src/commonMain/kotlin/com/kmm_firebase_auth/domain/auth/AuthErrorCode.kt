package com.kmm_firebase_auth.domain.auth

enum class AuthErrorCode {
    USER_NOT_FOUND,
    WRONG_PASSWORD,
    INVALID_EMAIL,
    EMAIL_ALREADY_IN_USE,
    NETWORK_ERROR,
    UNKNOWN
}