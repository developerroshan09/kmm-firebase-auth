//package com.kmm_firebase_auth.data.repository
//
////import com.google.firebase.auth.Auth
//import com.kmm_firebase_auth.domain.model.AuthState
//import com.kmm_firebase_auth.domain.model.User
//import com.kmm_firebase_auth.domain.repository.AuthRepository
//import kotlinx.coroutines.flow.Flow
//
//
//
//actual class iOSAuthRepositoryImpl: AuthRepository {
//
//    private val firebaseAuth = FirebaseAuth.shared
//
//    override suspend fun signUp(
//        email: String,
//        password: String
//    ): User {
//        TODO("Not yet implemented")
//    }
//
//    override suspend fun login(email: String, password: String): User {
//        TODO("Not yet implemented")
//    }
//
//    override suspend fun loginWithGoogle(idToken: String): Result<User> {
//        TODO("Not yet implemented")
//    }
//
//    override suspend fun logout(): Result<Unit> {
//        TODO("Not yet implemented")
//    }
//
//    override fun getAuthState(): Flow<AuthState> {
//        TODO("Not yet implemented")
//    }
//
//}