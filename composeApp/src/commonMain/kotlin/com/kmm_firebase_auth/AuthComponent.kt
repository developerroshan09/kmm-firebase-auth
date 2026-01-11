package com.kmm_firebase_auth

import com.kmm_firebase_auth.data.AuthRepositoryImpl
import com.kmm_firebase_auth.domain.repository.AuthRepository
import com.kmm_firebase_auth.domain.usecase.LoginUseCase
import com.kmm_firebase_auth.domain.usecase.LoginWithGoogleUseCase
import com.kmm_firebase_auth.domain.usecase.LogoutUseCase
import com.kmm_firebase_auth.domain.usecase.SignUpUseCase
import com.kmm_firebase_auth.platform.FirebaseAuthWrapper

class AuthComponent {

    private val authWrapper = FirebaseAuthWrapper()
    private val repository: AuthRepository = AuthRepositoryImpl(authWrapper)

    val loginUseCase = LoginUseCase(repository)
    val loginWithGoogleUseCase = LoginWithGoogleUseCase(repository)
    val signUpUseCase = SignUpUseCase(repository)
    val logoutUseCase = LogoutUseCase(repository)

    val authState = repository.observeAuthState()

    fun configure() {
        authWrapper.configure()
    }
}
