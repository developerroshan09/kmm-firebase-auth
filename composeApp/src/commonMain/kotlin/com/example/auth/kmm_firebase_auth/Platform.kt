package com.example.auth.kmm_firebase_auth

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform