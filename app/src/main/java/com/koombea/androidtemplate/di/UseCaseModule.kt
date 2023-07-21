package com.koombea.androidtemplate.di

import com.koombea.domain.usecase.LoginUseCase
import org.koin.dsl.module


val useCaseModule = module {
    single { LoginUseCase(authRepository = get()) }
}