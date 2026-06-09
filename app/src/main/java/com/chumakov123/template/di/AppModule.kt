package com.chumakov123.template.di

import com.chumakov123.template.domain.usecase.GetInitialStateUseCase
import com.chumakov123.template.navigation.AppEntryViewModel
import com.chumakov123.template.presentation.main.MainViewModel
import org.koin.core.module.dsl.factoryOf
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val appModule = module {
    factoryOf(::GetInitialStateUseCase)
    viewModelOf(::AppEntryViewModel)
    viewModelOf(::MainViewModel)
}
