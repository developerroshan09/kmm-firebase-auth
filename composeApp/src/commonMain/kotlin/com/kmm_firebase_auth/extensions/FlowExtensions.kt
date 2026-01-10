package com.kmm_firebase_auth.extensions

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

fun <T> Flow<T>.collectIn(
    scope: CoroutineScope,
    onEach: (T) -> Unit
) {
    scope.launch {
        this@collectIn.collect {
            onEach(it)
        }
    }
}