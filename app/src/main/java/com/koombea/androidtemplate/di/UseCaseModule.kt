package com.koombea.androidtemplate.di

import com.koombea.domain.usecase.LoginUseCase
import com.koombea.domain.usecase.SignUpUseCase
import org.koin.dsl.module


val useCaseModule = module {
    single { LoginUseCase(authRepository = get()) }
    single { SignUpUseCase(authRepository = get()) }
}