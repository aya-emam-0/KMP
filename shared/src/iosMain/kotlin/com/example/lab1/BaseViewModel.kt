package com.example.lab1

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel

actual open class BaseViewModel {
    actual val scope = CoroutineScope(Dispatchers.Main)
    fun onCleared() {
        scope.cancel()
    }
}