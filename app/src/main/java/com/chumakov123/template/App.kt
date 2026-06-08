package com.chumakov123.template

import android.app.Application
import com.chumakov123.template.core.di.coreModule
import com.chumakov123.template.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import timber.log.Timber

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }

        startKoin {
            androidContext(this@App)
            modules(coreModule, appModule)
        }
    }
}
