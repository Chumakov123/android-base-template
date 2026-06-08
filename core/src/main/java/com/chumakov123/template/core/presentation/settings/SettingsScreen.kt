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
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.chumakov123.template.core.R
import com.chumakov123.template.core.component.ScreenContainer
import com.chumakov123.template.core.component.SectionHeader
import com.chumakov123.template.core.component.SubHeader
import com.chumakov123.template.core.model.AppLanguage
import com.chumakov123.template.core.model.AppTheme
import com.chumakov123.template.core.theme.Spacing
import org.koin.androidx.compose.koinViewModel

@Composable
fun CoreSettingsScreen(
    viewModel: CoreSettingsViewModel = koinViewModel(),
    onBackClick: (() -> Unit)? = null
) {
    val state by viewModel.state.collectAsState()

    ScreenContainer {
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(Spacing.Small)
        ) {
            item {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    onBackClick?.let {
                        IconButton(onClick = it) {
                            Icon(
                                imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                                contentDescription = null
                            )
                        }
                    }
                    SectionHeader(
                        title = stringResource(R.string.settings_title),
                        icon = Icons.Default.Settings,
                        modifier = Modifier.padding(top = Spacing.Small)
                    )
                }
            }

            // Theme Section
            item {
                SubHeader(
                    title = stringResource(R.string.settings_section_theme),
                    icon = Icons.Default.Palette
                )
            }

            item {
                Column {
                    SettingsOption(
                        title = stringResource(R.string.settings_theme_system),
                        isSelected = state.theme == AppTheme.SYSTEM,
                        onClick = { viewModel.setTheme(AppTheme.SYSTEM) }
                    )
                    SettingsOption(
                        title = stringResource(R.string.settings_theme_light),
                        isSelected = state.theme == AppTheme.LIGHT,
                        onClick = { viewModel.setTheme(AppTheme.LIGHT) }
                    )
                    SettingsOption(
                        title = stringResource(R.string.settings_theme_dark),
                        isSelected = state.theme == AppTheme.DARK,
                        onClick = { viewModel.setTheme(AppTheme.DARK) }
                    )
                }
            }

            item { HorizontalDivider(Modifier.padding(vertical = Spacing.Medium)) }

            // Language Section
            item {
                SubHeader(
                    title = stringResource(R.string.settings_section_language),
                    icon = Icons.Default.Language
                )
            }

            item {
                Column {
                    SettingsOption(
                        title = stringResource(R.string.settings_language_system),
                        isSelected = state.language == AppLanguage.SYSTEM,
                        onClick = { viewModel.setLanguage(AppLanguage.SYSTEM) }
                    )
                    SettingsOption(
                        title = stringResource(R.string.settings_language_russian),
                        isSelected = state.language == AppLanguage.RUSSIAN,
                        onClick = { viewModel.setLanguage(AppLanguage.RUSSIAN) }
                    )
                    SettingsOption(
                        title = stringResource(R.string.settings_language_english),
                        isSelected = state.language == AppLanguage.ENGLISH,
                        onClick = { viewModel.setLanguage(AppLanguage.ENGLISH) }
                    )
                }
            }
        }
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
