package com.kmm_firebase_auth.domain.repository

import com.kmm_firebase_auth.domain.model.AuthState
import com.kmm_firebase_auth.domain.model.User
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

import cocoapods.FirebaseAuth.*
import cocoapods.FirebaseCore.*
import kotlinx.cinterop.ExperimentalForeignApi
import platform.Foundation.NSLog
import kotlin.experimental.ExperimentalObjCName

@OptIn(ExperimentalForeignApi::class, ExperimentalObjCName::class)
@ObjCName("FirebaseAuthWrapper")
public actual class FirebaseAuthWrapper {

    // Observed auth state for Flow
    private val _authState = MutableStateFlow<AuthState>(AuthState.LoggedOut)
    actual fun getAuthState(): Flow<AuthState> = _authState.asStateFlow()

    // Remove the 'by lazy' - just get auth directly when needed
    private fun getAuth(): FIRAuth {
        return FIRAuth.auth()
    }

    actual fun signInAnonymously() {
        NSLog("FirebaseTest: signInAnonymously() called")

        // Get auth instance only when this method executes
        val auth = getAuth()
        NSLog("FirebaseTest: FIRAuth.auth() obtained successfully")

        auth.signInAnonymouslyWithCompletion { result, error ->
            if (error == null) {
                val uid = result?.user()?.uid()
                NSLog("FirebaseTest: ✅ Anonymous login SUCCESS. UID: $uid")
                // Update state if needed
                // _authState.value = AuthState.LoggedIn(...)
            } else {
                NSLog("FirebaseTest: ❌ Login FAILED. Error: ${error.localizedDescription}")
            }
        }
    }

    actual fun signUp(email: String, password: String): User {
        TODO("Not yet implemented")
    }

    actual fun login(email: String, password: String) {
    }

    actual fun loginWithGoogle(idToken: String): Result<User> {
        TODO("Not yet implemented")
    }

    actual fun logout(): Result<Unit> {
        TODO("Not yet implemented")
    }
}