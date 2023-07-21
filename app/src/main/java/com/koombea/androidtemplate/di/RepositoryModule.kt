package com.koombea.androidtemplate.di

import com.koombea.data.character.repository.AuthRepository
import com.koombea.data.character.repository.AuthRepositoryImp
import org.koin.dsl.module

val repositoryModule = module {
    single<AuthRepository> {
        AuthRepositoryImp(
            authDataSource = get()
        )
    }
}