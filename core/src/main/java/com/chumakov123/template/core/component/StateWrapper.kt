package com.chumakov123.template.core.component

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.chumakov123.template.core.presentation.UiState

@Composable
fun <T> StateWrapper(
    uiState: UiState<T>,
    modifier: Modifier = Modifier,
    onRetry: () -> Unit = {},
    content: @Composable (T) -> Unit
) {
    Box(
        modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        when (uiState) {
            is UiState.Loading -> {
                LoadingState()
            }
            is UiState.Error -> {
                ErrorState(
                    message = uiState.message,
                    onRetry = onRetry
                )
            }
            is UiState.Success -> {
                content(uiState.data)
            }
            is UiState.Idle -> {
                // Do nothing or show placeholder
            }
        }
    }
}
