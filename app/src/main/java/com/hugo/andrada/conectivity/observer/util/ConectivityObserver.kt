package com.hugo.andrada.conectivity.observer.util

import kotlinx.coroutines.flow.Flow

interface ConnectivityObserver {

    fun observe(): Flow<Status>

    enum class Status {
        Available, Unvailable, Losing, Lost
    }
}