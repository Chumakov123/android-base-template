package com.chumakov123.template.core.domain.repository

import com.chumakov123.template.core.model.AppLanguage
import com.chumakov123.template.core.model.AppTheme
import kotlinx.coroutines.flow.Flow

interface CoreSettingsRepository {
    val selectedThemeFlow: Flow<AppTheme>
    val selectedLanguageFlow: Flow<AppLanguage>

    suspend fun setSelectedTheme(theme: AppTheme)
    suspend fun setSelectedLanguage(language: AppLanguage)
}
