package com.meypar.kernel.viewmodel

import androidx.lifecycle.ViewModel
import com.meypar.kernel.coroutines.mainContext
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