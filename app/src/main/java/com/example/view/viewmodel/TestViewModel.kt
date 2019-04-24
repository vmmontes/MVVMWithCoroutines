package com.example.view.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.kernel.coroutines.backgroundContext
import com.example.kernel.viewmodel.CoroutinesViewModel
import com.example.model.Data
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class TestViewModel(val data: Data = Data()): CoroutinesViewModel() {

    val projectListObservable: MutableLiveData<TestDataState> = MutableLiveData()
    val dataTest = TestDataState()

    init {
        onCoroutinesCreate()
    }

    override fun onCleared() {
        onCoroutinesDestroy()
        super.onCleared()
    }

    fun onClicked() {
        launch {
            async(backgroundContext) {
                delay(2000)
                getTestData()
            }.await().run {
                projectListObservable.value = this
            }
        }
    }

    fun onCleanClicked() {
        dataTest.state = TestDataState.State.cleanData
        dataTest.value = ""

        projectListObservable.value = dataTest
    }

    fun getTestData(): TestDataState {
        dataTest.state = TestDataState.State.showData
        dataTest.value = data.getValue()

        return dataTest
    }

    fun changeData(): LiveData<TestDataState> {
        return projectListObservable
    }
}