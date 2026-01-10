package com.kmm_firebase_auth.extensions

import com.kmm_firebase_auth.domain.model.AuthState
import com.kmm_firebase_auth.domain.repository.FirebaseAuthWrapper
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.MainScope

fun FirebaseAuthWrapper.collectAuthState(onEach: (AuthState) -> Unit) {
    val scope = MainScope()
    getAuthState().collectIn(scope) { state ->
        onEach(state)
    }
}
