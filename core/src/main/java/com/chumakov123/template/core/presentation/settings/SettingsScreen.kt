package com.chumakov123.template.core.presentation.settings

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Language
import androidx.compose.material.icons.filled.Palette
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.chumakov123.template.core.R
import com.chumakov123.template.core.component.SubHeader
import com.chumakov123.template.core.model.AppLanguage
import com.chumakov123.template.core.model.AppTheme
import com.chumakov123.template.core.theme.Spacing
import org.koin.androidx.compose.koinViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CoreSettingsScreen(
    viewModel: CoreSettingsViewModel = koinViewModel(),
    onBackClick: (() -> Unit)? = null
) {
    val state by viewModel.state.collectAsState()

    Scaffold(
        topBar = {
            SettingsTopBar(onBackClick)
        }
    ) { padding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding),
            verticalArrangement = Arrangement.spacedBy(Spacing.Small)
        ) {
            item {
                ThemeSection(
                    currentTheme = state.theme,
                    onThemeSelected = viewModel::setTheme
                )
            }

            item { HorizontalDivider(Modifier.padding(vertical = Spacing.Medium)) }

            item {
                LanguageSection(
                    currentLanguage = state.language,
                    onLanguageSelected = viewModel::setLanguage
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun SettingsTopBar(onBackClick: (() -> Unit)?) {
    TopAppBar(
        title = { Text(text = stringResource(R.string.settings_title)) },
        navigationIcon = {
            onBackClick?.let {
                IconButton(onClick = it) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = null
                    )
                }
            }
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.background,
            titleContentColor = MaterialTheme.colorScheme.onBackground,
            navigationIconContentColor = MaterialTheme.colorScheme.onBackground
        )
    )
}

@Composable
private fun ThemeSection(
    currentTheme: AppTheme,
    onThemeSelected: (AppTheme) -> Unit
) {
    Column {
        SubHeader(
            title = stringResource(R.string.settings_section_theme),
            icon = Icons.Default.Palette
        )
        SettingsOption(
            title = stringResource(R.string.settings_theme_system),
            isSelected = currentTheme == AppTheme.SYSTEM,
            onClick = { onThemeSelected(AppTheme.SYSTEM) }
        )
        SettingsOption(
            title = stringResource(R.string.settings_theme_light),
            isSelected = currentTheme == AppTheme.LIGHT,
            onClick = { onThemeSelected(AppTheme.LIGHT) }
        )
        SettingsOption(
            title = stringResource(R.string.settings_theme_dark),
            isSelected = currentTheme == AppTheme.DARK,
            onClick = { onThemeSelected(AppTheme.DARK) }
        )
    }
}

@Composable
private fun LanguageSection(
    currentLanguage: AppLanguage,
    onLanguageSelected: (AppLanguage) -> Unit
) {
    Column {
        SubHeader(
            title = stringResource(R.string.settings_section_language),
            icon = Icons.Default.Language
        )
        SettingsOption(
            title = stringResource(R.string.settings_language_system),
            isSelected = currentLanguage == AppLanguage.SYSTEM,
            onClick = { onLanguageSelected(AppLanguage.SYSTEM) }
        )
        SettingsOption(
            title = stringResource(R.string.settings_language_russian),
            isSelected = currentLanguage == AppLanguage.RUSSIAN,
            onClick = { onLanguageSelected(AppLanguage.RUSSIAN) }
        )
        SettingsOption(
            title = stringResource(R.string.settings_language_english),
            isSelected = currentLanguage == AppLanguage.ENGLISH,
            onClick = { onLanguageSelected(AppLanguage.ENGLISH) }
        )
    }
}

@Composable
private fun SettingsOption(
    title: String,
    isSelected: Boolean,
    onClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick)
            .padding(horizontal = Spacing.Medium, vertical = Spacing.Small),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = title,
            style = MaterialTheme.typography.bodyLarge
        )
        RadioButton(
            selected = isSelected,
            onClick = onClick
        )
    }
}
