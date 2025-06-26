package com.nicholas.rutherford.moments

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.nicholas.rutherford.moments.data.converter.CategoryTagConverter
import com.nicholas.rutherford.moments.room.NoteDao
import com.nicholas.rutherford.moments.room.NoteEntity

@Database(entities = [NoteEntity::class], version = 1)
@TypeConverters(CategoryTagConverter::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun noteDao(): NoteDao
}