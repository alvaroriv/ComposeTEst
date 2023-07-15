package com.koombea.androidtemplate.di

import com.koombea.domain.usecase.GetRootStatusUseCase
import org.koin.dsl.module


val useCaseModule = module {
    single { GetRootStatusUseCase(rootCheckerRepository = get()) }
}