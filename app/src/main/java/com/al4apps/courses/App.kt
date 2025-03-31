package com.al4apps.courses

import android.app.Application
import com.al4apps.courses.di.appModule
import com.al4apps.data.di.dataModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@App)
            modules(appModule, dataModule)
        }
    }
}