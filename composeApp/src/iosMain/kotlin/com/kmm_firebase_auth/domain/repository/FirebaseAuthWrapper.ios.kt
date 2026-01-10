package com.kmm_firebase_auth.domain.repository

import com.kmm_firebase_auth.domain.model.AuthState
import com.kmm_firebase_auth.domain.model.User
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

import cocoapods.FirebaseAuth.*
import cocoapods.FirebaseAuth.FIRAuth.Companion.auth
import cocoapods.FirebaseCore.*
import kotlinx.cinterop.ExperimentalForeignApi
import kotlinx.coroutines.suspendCancellableCoroutine
import platform.Foundation.NSLog
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
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

    actual fun configure() {
        FIRApp.configure()
        if (FIRApp.defaultApp() != null) {
            NSLog("✅ FirebaseApp successfully configured.")
        } else {
            NSLog("❌ FirebaseApp configuration FAILED.")
        }
    }

    private fun observeAuthState() {
        getAuth().addAuthStateDidChangeListener { _, user ->
            if (user != null) {
                _authState.value = AuthState.LoggedIn(
                    User(
                        id = user.uid(),
                        email = user.email(),
                    )
                )
            } else {
                _authState.value = AuthState.LoggedOut
            }
        }
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

    actual suspend fun signUp(email: String, password: String): User =
            suspendCancellableCoroutine { continuation ->

                auth().createUserWithEmail(email = email, password = password) { result, error ->
                    if (error != null) {
                        continuation.resumeWithException(Exception(error.localizedDescription))
                        return@createUserWithEmail
                    }

                    val firebaseUser = result?.user()
                    if (firebaseUser != null) {
                        val user = User(id = firebaseUser.uid(), email = firebaseUser.email())
                        _authState.value = AuthState.LoggedIn(user)
                        continuation.resume(user)
                    } else {
                        continuation.resumeWithException(Exception("Firebase returned null user"))
                    }
                }
            }


    actual suspend fun login(email: String, password: String): User =
        suspendCancellableCoroutine { continuation ->
            auth().signInWithEmail(email = email, password = password) { result, error ->
                if (error != null) {
                    continuation.resumeWithException(Exception(error.localizedDescription))
                    return@signInWithEmail
                }

                val firebaseUser = result?.user()
                if (firebaseUser != null) {
                    val user = User(id = firebaseUser.uid(), email = firebaseUser.email())
                    _authState.value = AuthState.LoggedIn(user)
                    continuation.resume(user)
                } else {
                    continuation.resumeWithException(Exception("Firebase returned null user"))
                }
            }
        }


    actual suspend fun loginWithGoogle(idToken: String): User =
        suspendCancellableCoroutine { continuation ->

            val credential = FIRGoogleAuthProvider.credentialWithIDToken(
                idToken = idToken,
                accessToken = ""
            )

            getAuth().signInWithCredential(credential) { result, error ->
                if (error != null) {
                    continuation.resumeWithException(Exception(error.localizedDescription))
                    return@signInWithCredential
                }

                val firebaseUser = result?.user()
                if (firebaseUser != null) {
                    val user = User(
                        id = firebaseUser.uid(),
                        email = firebaseUser.email()
                    )
                    _authState.value = AuthState.LoggedIn(user)
                    continuation.resume(user)
                } else {
                    continuation.resumeWithException(Exception("Google login returned null user"))
                }
            }
        }

//    actual fun logout(): Result<Unit> = try {
//        auth().signOut(null)
//        Result.success(Unit)
//    } catch (e: Throwable) {
//        Result.failure(e)
//    }

//    actual fun logout(): Result<Unit> = try {
//        auth().signOut(null)
//        Result.success(Unit)
//    } catch (e: Throwable) {
//        Result.failure(e)
//    }
    actual suspend fun logout(): Result<Unit> {
        try {
            auth().signOut(null)
            _authState.value = AuthState.LoggedOut
            Result.success(Unit)
        } catch (e: Throwable) {
            Result.failure(e)
        }
        return Result.failure(IllegalStateException("Firebase logout() failed"))
    }
}