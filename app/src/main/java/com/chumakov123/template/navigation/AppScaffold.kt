package com.chumakov123.template.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import com.chumakov123.template.core.component.ScreenContainer
import org.koin.androidx.compose.koinViewModel

@Composable
fun AppScaffold(
    viewModel: AppEntryViewModel = koinViewModel()
) {
    val state by viewModel.state.collectAsState()

    if (state.isLoading) {
        ScreenContainer { }
        return
    }

    Scaffold { innerPadding ->
        NavGraph(
            modifier = Modifier.padding(innerPadding)
        )
    }
}
