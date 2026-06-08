package com.chumakov123.template

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.chumakov123.template.core.theme.AppCoreTheme
import com.chumakov123.template.navigation.AppEntryViewModel
import com.chumakov123.template.navigation.AppScaffold
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {
    
    private val viewModel: AppEntryViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        val splashScreen = installSplashScreen()
        super.onCreate(savedInstanceState)
        
        enableEdgeToEdge()
        
        splashScreen.setKeepOnScreenCondition {
            viewModel.state.value.isLoading
        }
        
        setContent {
            val state by viewModel.state.collectAsState()
            
            AppCoreTheme(appTheme = state.theme) {
                AppScaffold()
            }
        }
    }
}
