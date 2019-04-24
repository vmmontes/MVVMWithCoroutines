package com.example.kernel.coroutines

import android.util.Log
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlin.coroutines.AbstractCoroutineContextElement
import kotlin.coroutines.CoroutineContext

val mainContext = Dispatchers.Main + UncaughtCoRoutineExceptionHandler()
val backgroundContext = Dispatchers.IO + UncaughtCoRoutineExceptionHandler()

class UncaughtCoRoutineExceptionHandler :
    CoroutineExceptionHandler, AbstractCoroutineContextElement(CoroutineExceptionHandler.Key) {
    override fun handleException(context: CoroutineContext, exception: Throwable) {
        Log.e("coroutinesError", exception.message.toString())
    }
}