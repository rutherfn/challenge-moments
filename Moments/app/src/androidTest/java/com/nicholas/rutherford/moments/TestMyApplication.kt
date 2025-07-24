package com.nicholas.rutherford.moments

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class TestMyApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@TestMyApplication)
            modules(testAppModule)
        }
    }
}
