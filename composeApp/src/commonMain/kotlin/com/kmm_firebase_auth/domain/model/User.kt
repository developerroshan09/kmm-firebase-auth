package com.kmm_firebase_auth.domain.model

// Core user entity in the domain layer
data class User (
    val id: String,
    val email: String?
)