package com.chumakov123.template.core.data.settings

import android.content.Context
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore

val Context.coreDataStore by preferencesDataStore(name = "core_settings")

object CoreSettingsKeys {
    val SELECTED_THEME = stringPreferencesKey("selected_theme")
    val SELECTED_LANGUAGE = stringPreferencesKey("selected_language")
}
