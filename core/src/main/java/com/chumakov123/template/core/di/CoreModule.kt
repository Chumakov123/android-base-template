package com.chumakov123.template.core.di

import com.chumakov123.template.core.data.repository.DataStoreCoreSettingsRepository
import com.chumakov123.template.core.domain.repository.CoreSettingsRepository
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val coreModule = module {
    single<CoreSettingsRepository> {
        DataStoreCoreSettingsRepository(context = androidContext())
    }
}
