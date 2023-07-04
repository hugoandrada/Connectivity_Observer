package com.hugo.andrada.conectivity.observer

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hugo.andrada.conectivity.observer.util.ConnectivityObserver
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val connectivity: ConnectivityObserver
): ViewModel() {

    private val _status = MutableStateFlow<ConnectivityObserver.Status>(ConnectivityObserver.Status.Unvailable)
    val status = _status.asStateFlow()

    init {
        observe()
    }

    private fun observe() {
        viewModelScope.launch {
            connectivity.observe().collectLatest { response ->
                _status.value = response
            }
        }
    }
}