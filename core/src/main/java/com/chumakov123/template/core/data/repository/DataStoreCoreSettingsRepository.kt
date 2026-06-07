package com.chumakov123.template.core.data.repository

import android.content.Context
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.os.LocaleListCompat
import androidx.datastore.preferences.core.edit
import com.chumakov123.template.core.data.settings.CoreSettingsKeys
import com.chumakov123.template.core.data.settings.coreDataStore
import com.chumakov123.template.core.domain.repository.CoreSettingsRepository
import com.chumakov123.template.core.model.AppLanguage
import com.chumakov123.template.core.model.AppTheme
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext

class DataStoreCoreSettingsRepository(
    private val context: Context
) : CoreSettingsRepository {

    override val selectedThemeFlow: Flow<AppTheme> =
        context.coreDataStore.data.map { prefs ->
            val themeName = prefs[CoreSettingsKeys.SELECTED_THEME] ?: AppTheme.DARK.name
            try {
                AppTheme.valueOf(themeName)
            } catch (e: Exception) {
                AppTheme.DARK
            }
        }

    override val selectedLanguageFlow: Flow<AppLanguage> =
        context.coreDataStore.data.map { prefs ->
            val languageName = prefs[CoreSettingsKeys.SELECTED_LANGUAGE] ?: AppLanguage.SYSTEM.name
            try {
                AppLanguage.valueOf(languageName)
            } catch (e: Exception) {
                AppLanguage.SYSTEM
            }
        }

    override suspend fun setSelectedTheme(theme: AppTheme) {
        context.coreDataStore.edit { prefs ->
            prefs[CoreSettingsKeys.SELECTED_THEME] = theme.name
        }
    }

    override suspend fun setSelectedLanguage(language: AppLanguage) {
        val targetLocales = if (language == AppLanguage.SYSTEM) {
            LocaleListCompat.getEmptyLocaleList()
        } else {
            LocaleListCompat.forLanguageTags(language.code)
        }

        withContext(Dispatchers.Main) {
            if (AppCompatDelegate.getApplicationLocales().toLanguageTags() != targetLocales.toLanguageTags()) {
                AppCompatDelegate.setApplicationLocales(targetLocales)
            }
        }

        context.coreDataStore.edit { prefs ->
            prefs[CoreSettingsKeys.SELECTED_LANGUAGE] = language.name
        }
    }
}
