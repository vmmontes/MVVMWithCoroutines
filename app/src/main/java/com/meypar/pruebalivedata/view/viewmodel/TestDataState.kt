package com.meypar.pruebalivedata.view.viewmodel

class TestDataState(
    var state: State = State.cleanData,
    var value: String = "") {

    enum class State {
        cleanData, showData
    }
}