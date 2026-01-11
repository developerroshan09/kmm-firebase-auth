package com.kmm_firebase_auth.extensions

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlin.experimental.ExperimentalObjCName
import kotlin.native.ObjCName

@OptIn(ExperimentalObjCName::class)
@ObjCName("FlowObserverFactory")
public object FlowObserverFactory {

    @ObjCName("observeAuthState")
    fun <T> observe(
        flow: Flow<T>,
        scope: CoroutineScope,
        onEach: (T) -> Unit
    ): FlowObserver<T> {
        return FlowObserver(scope, flow, onEach)
    }
}
