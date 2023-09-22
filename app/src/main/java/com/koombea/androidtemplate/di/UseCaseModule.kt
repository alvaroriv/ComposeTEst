package com.koombea.androidtemplate.di

import com.koombea.domain.usecase.AddTransactionUseCase
import com.koombea.domain.usecase.EditProfileUseCase
import com.koombea.domain.usecase.GetTransactionsUseCase
import com.koombea.domain.usecase.GetUserUseCase
import com.koombea.domain.usecase.LoginUseCase
import com.koombea.domain.usecase.SignOutUseCase
import com.koombea.domain.usecase.SignUpUseCase
import org.koin.dsl.module


val useCaseModule = module {
    single { LoginUseCase(authRepository = get()) }
    single { SignUpUseCase(authRepository = get()) }
    single { GetUserUseCase(authRepository = get()) }
    single { GetTransactionsUseCase(transactionRepository = get()) }
    single { AddTransactionUseCase(transactionRepository = get()) }
    single { SignOutUseCase(authRepository = get()) }
    single { EditProfileUseCase(authRepository = get()) }
}