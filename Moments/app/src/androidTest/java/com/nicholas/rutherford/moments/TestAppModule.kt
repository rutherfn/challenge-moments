package com.nicholas.rutherford.moments

import androidx.lifecycle.SavedStateHandle
import androidx.room.Room
import com.nicholas.rutherford.moments.createeditmoment.CreateEditMomentViewModel
import com.nicholas.rutherford.moments.home.HomeViewModel
import com.nicholas.rutherford.moments.navigation.Navigator
import com.nicholas.rutherford.moments.navigation.NavigatorImpl
import com.nicholas.rutherford.moments.repository.MomentRepository
import com.nicholas.rutherford.moments.repository.MomentRepositoryImpl
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import org.koin.android.ext.koin.androidApplication
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

// Coroutine scope used for background operations for testing
val testDefaultCoroutineScope: CoroutineScope = CoroutineScope(SupervisorJob() + Dispatchers.Default)

val testAppModule = module {
    // Provide a single instance of the Room database for testing
    single {
        Room.databaseBuilder(
            androidApplication(),
            AppDatabase::class.java, // Assuming AppDatabase class is accessible
            "moments_database_test.db" // Use a different database name for tests
        ).allowMainThreadQueries() // Allow main thread queries for testing simplicity
         .build()
    }

    // Provide a single instance of MomentDao for database access
    single {
        get<AppDatabase>().momentDao() // Assuming AppDatabase class is accessible
    }

    // Provide a single instance of Navigator, responsible for navigation actions
    single<Navigator> {
        NavigatorImpl()
    }

    // Provide a single instance of MomentRepository, which interacts with the DAO
    single<MomentRepository> {
        MomentRepositoryImpl(get()) // Passing the MomentDao instance
    }

    // Provide the HomeViewModel, injected with necessary dependencies
    viewModel {
        HomeViewModel(
            scope = testDefaultCoroutineScope,
            momentRepository = get(),
            navigator = get()
        )
    }

    // Provide the CreateEditMomentViewModel, with SavedStateHandle for screen state management
    viewModel { (stateHandle: SavedStateHandle) ->
        CreateEditMomentViewModel(
            savedStateHandle = stateHandle,
            application = androidApplication(),
            momentRepository = get(),
            scope = testDefaultCoroutineScope,
            navigator = get()
        )
    }
}
