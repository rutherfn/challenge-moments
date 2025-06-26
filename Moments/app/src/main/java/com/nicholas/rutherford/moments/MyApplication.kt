package com.nicholas.rutherford.moments

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

open class MyApplication : Application() {

    override fun onCreate() {
        startKoinOnCreate()
    }

    fun startKoinOnCreate() {
        startKoin {
            androidContext(this@MyApplication)
            modules(AppModule().modules)
        }
    }
}