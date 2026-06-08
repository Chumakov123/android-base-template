package com.chumakov123.template.core.presentation.settings

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.chumakov123.template.core.domain.repository.CoreSettingsRepository
import com.chumakov123.template.core.model.AppLanguage
import com.chumakov123.template.core.model.AppTheme
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

data class CoreSettingsState(
    val theme: AppTheme = AppTheme.DARK,
    val language: AppLanguage = AppLanguage.SYSTEM
)

class CoreSettingsViewModel(
    private val repository: CoreSettingsRepository
) : ViewModel() {

    private val _state = MutableStateFlow(CoreSettingsState())
    val state = _state.asStateFlow()

    init {
        observeSettings()
    }

    private fun observeSettings() {
        viewModelScope.launch(Dispatchers.IO) {
            repository.selectedThemeFlow.collectLatest { theme ->
                _state.update { it.copy(theme = theme) }
            }
        }
        viewModelScope.launch(Dispatchers.IO) {
            repository.selectedLanguageFlow.collectLatest { lang ->
                _state.update { it.copy(language = lang) }
            }
        }
    }

    fun setTheme(theme: AppTheme) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.setSelectedTheme(theme)
        }
    }

    fun setLanguage(language: AppLanguage) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.setSelectedLanguage(language)
        }
    }
}
