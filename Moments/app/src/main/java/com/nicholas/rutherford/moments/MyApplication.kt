package com.nicholas.rutherford.moments

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import timber.log.Timber

/**
 * The `MyApplication` class is an `Application` subclass used to initialize global configurations and dependencies for the app.
 * This class is responsible for setting up Koin (for dependency injection) and Timber (for logging) when the application is created.
 */
class MyApplication : Application() {

    /**
     * Called when the application is first created. This method initializes the Koin dependency injection framework
     * and Timber logging library.
     */
    override fun onCreate() {
        super.onCreate()

        // Initialize Koin for dependency injection
        startKoinOnCreate()

        // Initialize Timber for logging (only in DEBUG builds)
        startTimberOnCreate()
    }

    /**
     * Initializes Koin by providing the application context and loading the necessary Koin modules.
     */
    private fun startKoinOnCreate() {
        startKoin {
            androidContext(this@MyApplication)  // Provide the application context to Koin
            modules(modules = AppModule().modules)  // Load Koin modules from AppModule
        }
    }

    /**
     * Initializes Timber for logging purposes. In debug builds, it sets up the Timber DebugTree to log detailed information.
     * Timber is a third-party library used for logging in Android applications.
     */
    private fun startTimberOnCreate() {
        // Only initialize Timber in DEBUG mode
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())  // Plant a debug tree for logging in debug builds
        }
    }
}

