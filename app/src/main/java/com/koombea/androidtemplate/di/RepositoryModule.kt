package com.koombea.androidtemplate.di

import com.koombea.data.character.repository.RootCheckerRepository
import com.koombea.data.character.repository.RootCheckerRepositoryImp
import org.koin.dsl.module

val repositoryModule = module {
    single<RootCheckerRepository> {
        RootCheckerRepositoryImp(
            rootCheckerDataSource = get()
        )
    }
}