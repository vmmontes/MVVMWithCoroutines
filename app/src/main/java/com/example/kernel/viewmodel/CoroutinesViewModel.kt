package com.example.kernel.viewmodel

import androidx.lifecycle.ViewModel
import com.example.kernel.coroutines.mainContext
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlin.coroutines.CoroutineContext

abstract class CoroutinesViewModel : ViewModel(), CoroutineScope {
    lateinit var job: Job

    override val coroutineContext: CoroutineContext
        get() = job + mainContext


    fun onCoroutinesCreate() {
        job = Job()
    }

    fun onCoroutinesDestroy() {
        job.cancel()
    }
}