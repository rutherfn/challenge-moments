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
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

// AppModule sets up dependency injection using Koin for the application.
class AppModule {
    // Coroutine scope used for background operations
    private val defaultCoroutineScope: CoroutineScope = CoroutineScope(SupervisorJob() + Dispatchers.Default)

    // Koin module to provide dependencies for the application
    val modules = module {
        // Provide a single instance of the Room database
        single {
            Room.databaseBuilder(
                androidApplication(),
                AppDatabase::class.java,
                "moments_database.db" // Database name
            ).build()
        }

        // Provide a single instance of MomentDao for database access
        single {
            get<AppDatabase>().momentDao()
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
                scope = defaultCoroutineScope,
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
                scope = defaultCoroutineScope,
                navigator = get()
            )
        }
    }
}
