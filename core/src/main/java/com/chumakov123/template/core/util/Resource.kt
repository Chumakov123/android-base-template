package com.chumakov123.template.core.util

sealed class Resource<out T> {
    data class Success<out T>(val data: T) : Resource<T>()
    data class Error(val message: UiText) : Resource<Nothing>()
    object Loading : Resource<Nothing>()
}
