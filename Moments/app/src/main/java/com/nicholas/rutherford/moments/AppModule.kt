package com.nicholas.rutherford.moments

import androidx.room.Room
import com.nicholas.rutherford.moments.repository.NoteRepositoryImpl
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module

class AppModule {
    private val defaultCoroutineScope: CoroutineScope = CoroutineScope(SupervisorJob() + Dispatchers.Default)

    val modules = module {
        single {
            Room.databaseBuilder(
                androidApplication(),
                AppDatabase::class.java,
                "app_database.db"
            ).build()
        }
        single {
            get<AppDatabase>().noteDao()
        }
        single {
            NoteRepositoryImpl(get())
        }
    }
}