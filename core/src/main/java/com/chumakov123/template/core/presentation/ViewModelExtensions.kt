package com.chumakov123.template.core.presentation

import com.chumakov123.template.core.util.Resource
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update

fun <T> MutableStateFlow<UiState<T>>.handleResource(resource: Resource<T>) {
    this.update {
        when (resource) {
            is Resource.Loading -> UiState.Loading
            is Resource.Success -> UiState.Success(resource.data)
            is Resource.Error -> UiState.Error(resource.message)
        }
    }
}
