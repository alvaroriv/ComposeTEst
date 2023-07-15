package com.koombea.androidtemplate.di

import com.koombea.data.character.datasource.RootCheckerDataSource
import com.koombea.data.character.datasource.RootCheckerDataSourceImpl
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module


val dataSourceModule = module {
    single<RootCheckerDataSource> {
        RootCheckerDataSourceImpl(androidContext())
    }
}
