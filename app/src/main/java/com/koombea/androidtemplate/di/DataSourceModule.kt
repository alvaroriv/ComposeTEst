package com.koombea.androidtemplate.di

import com.koombea.couchbasewrapper.database.CouchbaseDatabase
import com.koombea.data.character.datasource.AuthDataSource
import com.koombea.data.character.datasource.AuthDataSourceImpl
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module


val dataSourceModule = module {
    single<AuthDataSource> {
        AuthDataSourceImpl(androidContext(), couchbaseDatabase = get())
    }

    single {
        CouchbaseDatabase(androidContext(), "users")
    }
}
