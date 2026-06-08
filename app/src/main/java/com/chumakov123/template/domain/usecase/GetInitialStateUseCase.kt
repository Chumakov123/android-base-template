package com.chumakov123.template.domain.usecase

import com.chumakov123.template.core.domain.repository.CoreSettingsRepository
import com.chumakov123.template.core.model.AppTheme
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

data class InitialState(
    val theme: AppTheme
)

class GetInitialStateUseCase(
    private val repository: CoreSettingsRepository
) {
    operator fun invoke(): Flow<InitialState> {
        return repository.selectedThemeFlow.map { theme ->
            InitialState(theme = theme)
        }
    }
}
