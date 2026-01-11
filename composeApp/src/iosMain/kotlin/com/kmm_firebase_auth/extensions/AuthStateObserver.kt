package com.kmm_firebase_auth.extensions

import com.kmm_firebase_auth.domain.model.AuthState
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlin.experimental.ExperimentalObjCName
import kotlin.native.ObjCName

@OptIn(ExperimentalObjCName::class)
@ObjCName("AuthStateObserver")
public class AuthStateObserver(
    scope: CoroutineScope,
    flow: Flow<AuthState>,
    onEach: (AuthState) -> Unit
) {
    private val observer = FlowObserver(
        scope = scope,
        flow = flow,
        onEach = onEach
    )

    fun cancel() {
        observer.cancel()
    }
}
