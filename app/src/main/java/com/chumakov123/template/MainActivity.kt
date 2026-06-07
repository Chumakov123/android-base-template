package com.chumakov123.template

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.chumakov123.template.core.domain.repository.CoreSettingsRepository
import com.chumakov123.template.core.theme.AppCoreTheme
import org.koin.android.ext.android.inject

class MainActivity : AppCompatActivity() {
    
    private val settingsRepository: CoreSettingsRepository by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val theme by settingsRepository.selectedThemeFlow.collectAsState(initial = com.chumakov123.template.core.model.AppTheme.SYSTEM)
            
            AppCoreTheme(appTheme = theme) {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Text(text = "Hello Template!")
                }
            }
        }
    }
}
