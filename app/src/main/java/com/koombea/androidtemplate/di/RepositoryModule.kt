package com.koombea.androidtemplate.di

import com.koombea.data.character.repository.AuthRepository
import com.koombea.data.character.repository.AuthRepositoryImp
import com.koombea.data.character.repository.TransactionRepository
import com.koombea.data.character.repository.TransactionRepositoryImp
import org.koin.dsl.module

val repositoryModule = module {
    single<AuthRepository> {
        AuthRepositoryImp(
            authDataSource = get()
        )
    }
    single<TransactionRepository> {
        TransactionRepositoryImp(
            transactionDataSource = get()
        )
    }
}