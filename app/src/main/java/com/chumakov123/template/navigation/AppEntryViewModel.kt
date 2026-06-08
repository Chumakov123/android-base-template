package com.chumakov123.template.navigation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.chumakov123.template.core.model.AppTheme
import com.chumakov123.template.domain.usecase.GetInitialStateUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

data class AppEntryState(
    val isLoading: Boolean = true,
    val theme: AppTheme = AppTheme.SYSTEM
)

class AppEntryViewModel(
    private val getInitialStateUseCase: GetInitialStateUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(AppEntryState())
    val state: StateFlow<AppEntryState> = _state

    init {
        viewModelScope.launch {
            getInitialStateUseCase().collectLatest { initial ->
                _state.value = AppEntryState(
                    isLoading = false,
                    theme = initial.theme
                )
            }
        }
    }
}
