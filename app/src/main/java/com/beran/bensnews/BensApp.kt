package com.beran.bensnews

import android.app.Application
import com.beran.core.di.networkModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class BensApp: Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(this@BensApp)
            modules(listOf(
                networkModule
            ))
        }
    }
}