package com.mnhyim.moviecatalog

import android.app.Application
import com.mnhyim.core.di.databaseModule
import com.mnhyim.core.di.networkModule
import com.mnhyim.core.di.repositoryModule
import com.mnhyim.moviecatalog.di.useCaseModule
import com.mnhyim.moviecatalog.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger(Level.NONE)
            androidContext(this@MyApplication)
            modules(
                listOf(
                    databaseModule,
                    networkModule,
                    repositoryModule,
                    useCaseModule,
                    viewModelModule
                )
            )
        }
    }
}