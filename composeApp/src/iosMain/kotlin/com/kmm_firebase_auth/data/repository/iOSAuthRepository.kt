package com.kmm_firebase_auth.data.repository

//import com.google.firebase.auth.Auth
import com.kmm_firebase_auth.domain.model.User
import com.kmm_firebase_auth.domain.repository.AuthRepository
import kotlinx.coroutines.flow.Flow



class iOSAuthRepositoryImpl: AuthRepository {


    override suspend fun signUp(
        email: String,
        password: String
    ): User {
        TODO("Not yet implemented")
    }

    override suspend fun login(email: String, password: String): User {
        TODO("Not yet implemented")
    }

    override suspend fun logout() {
        TODO("Not yet implemented")
    }

    override fun getAuthState(): Flow<User?> {
        TODO("Not yet implemented")
    }

}