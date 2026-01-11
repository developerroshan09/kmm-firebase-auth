package com.kmm_firebase_auth.extensions

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

public class FlowObserver<T>(
    private val scope: CoroutineScope,
    private val flow: Flow<T>,
    private val onEach: (T) -> Unit
) {
    private val job = scope.launch {
        flow.collect { value ->
            onEach(value)
        }
    }

    fun cancel() {
        job.cancel()
    }
}
