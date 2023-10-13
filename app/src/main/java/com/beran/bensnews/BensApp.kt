package com.beran.bensnews

import android.app.Application
import com.beran.bensnews.di.useCaseModule
import com.beran.bensnews.di.viewModelModule
import com.beran.core.di.databaseModule
import com.beran.core.di.networkModule
import com.beran.core.di.repositoryModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class BensApp : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(this@BensApp)
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