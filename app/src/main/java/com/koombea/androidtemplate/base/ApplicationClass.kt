package com.koombea.androidtemplate.base

import ViewModelModule
import android.app.Application
import com.koombea.androidtemplate.di.*
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class ApplicationClass: Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@ApplicationClass)
            modules(
                    listOf(
                            networkModule,
                            dataSourceModule,
                            repositoryModule,
                            useCaseModule,
                            ViewModelModule
                    )
            )
        }
    }
}