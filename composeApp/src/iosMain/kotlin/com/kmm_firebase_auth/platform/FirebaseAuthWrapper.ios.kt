package com.kmm_firebase_auth.platform

import com.kmm_firebase_auth.domain.model.AuthState
import com.kmm_firebase_auth.domain.model.User
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

import cocoapods.FirebaseAuth.*
import cocoapods.FirebaseAuth.FIRAuth.Companion.auth
import cocoapods.FirebaseCore.*
import com.kmm_firebase_auth.domain.auth.AuthError
import com.kmm_firebase_auth.domain.auth.AuthErrorCode
import com.kmm_firebase_auth.domain.auth.AuthResult
import kotlinx.cinterop.ExperimentalForeignApi
import kotlinx.coroutines.suspendCancellableCoroutine
import platform.Foundation.NSError
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
        return auth()
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

    actual suspend fun signUp(email: String, password: String): AuthResult<User> =
            suspendCancellableCoroutine { continuation ->
                auth().createUserWithEmail(email = email, password = password) { result, error ->
                    continuation.resume(handleAuthResult(result, error))
                }
            }


    actual suspend fun login(email: String, password: String): AuthResult<User> =
        suspendCancellableCoroutine { continuation ->
            auth().signInWithEmail(email = email, password = password) { result, error ->
                continuation.resume(handleAuthResult(result, error))
            }
        }


    actual suspend fun loginWithGoogle(idToken: String): AuthResult<User> =
        suspendCancellableCoroutine { continuation ->

            val credential = FIRGoogleAuthProvider.credentialWithIDToken(
                idToken = idToken,
                accessToken = ""
            )

            getAuth().signInWithCredential(credential) { result, error ->
                continuation.resume(handleAuthResult(result, error))
            }
        }


    actual suspend fun logout(): AuthResult<Unit> =
        try {
            auth().signOut(null)
            _authState.value = AuthState.LoggedOut
            AuthResult.Success(Unit)
        } catch (t: Throwable) {
            AuthResult.Failure(
                AuthError.Firebase(
                    code = AuthErrorCode.UNKNOWN,
                    message = t.message
                )
            )
        }


    @OptIn(ExperimentalForeignApi::class)
    private fun handleAuthResult(
        result: FIRAuthDataResult?,
        error: NSError?
    ): AuthResult<User> {
        return when {
            error != null -> {
                AuthResult.Failure(mapFirebaseError(error))
            }

            result?.user() != null -> {
                val firebaseUser = result.user()!!
                val user = User(
                    id = firebaseUser.uid(),
                    email = firebaseUser.email()
                )
                _authState.value = AuthState.LoggedIn(user)
                AuthResult.Success(user)
            }

            else -> {
                AuthResult.Failure(
                    AuthError.Firebase(
                        code = AuthErrorCode.UNKNOWN,
                        message = "Firebase returned null user"
                    )
                )
            }
        }
    }

    private fun mapFirebaseError(error: NSError): AuthError {
        val code = when (error.code.toInt()) {
            FirebaseAuthErrorCodes.USER_NOT_FOUND ->
                AuthErrorCode.USER_NOT_FOUND

            FirebaseAuthErrorCodes.WRONG_PASSWORD ->
                AuthErrorCode.WRONG_PASSWORD

            FirebaseAuthErrorCodes.INVALID_EMAIL ->
                AuthErrorCode.INVALID_EMAIL

            FirebaseAuthErrorCodes.EMAIL_ALREADY_IN_USE ->
                AuthErrorCode.EMAIL_ALREADY_IN_USE

            FirebaseAuthErrorCodes.NETWORK_ERROR ->
                AuthErrorCode.NETWORK_ERROR

            else ->
                AuthErrorCode.UNKNOWN
        }

        return AuthError.Firebase(
            code = code,
            message = error.localizedDescription
        )
    }
}

// Firebase iOS Auth error codes (stable & documented)
private object FirebaseAuthErrorCodes {
    const val USER_NOT_FOUND = 17011
    const val WRONG_PASSWORD = 17009
    const val INVALID_EMAIL = 17008
    const val EMAIL_ALREADY_IN_USE = 17007
    const val NETWORK_ERROR = 17020
}
