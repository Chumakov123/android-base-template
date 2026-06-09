package com.chumakov123.template.presentation.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.chumakov123.template.core.presentation.UiState
import com.chumakov123.template.core.presentation.handleResource
import com.chumakov123.template.core.util.Resource
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlin.time.Duration.Companion.milliseconds

class MainViewModel : ViewModel() {

    private val _state = MutableStateFlow<UiState<String>>(UiState.Loading)
    val state = _state.asStateFlow()

    init {
        loadData()
    }

    fun loadData() {
        viewModelScope.launch {
            _state.handleResource(Resource.Loading)

            delay(1500.milliseconds)
            
            val isSuccess = true
            
            if (isSuccess) {
                _state.handleResource(Resource.Success("Data Loaded Successfully!"))
            } else {
                _state.handleResource(Resource.Error("Failed to load data"))
            }
        }
    }
}
