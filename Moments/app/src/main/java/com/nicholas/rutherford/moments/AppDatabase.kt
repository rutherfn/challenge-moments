package com.nicholas.rutherford.moments

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.nicholas.rutherford.moments.data.converter.CategoryTagConverter
import com.nicholas.rutherford.moments.room.MomentDao
import com.nicholas.rutherford.moments.room.MomentEntity

// AppDatabase for managing MomentEntity with Room.
@Database(entities = [MomentEntity::class], version = 1)  // Defines the database with version 1
@TypeConverters(CategoryTagConverter::class)              // Use custom converters for CategoryTag
abstract class AppDatabase : RoomDatabase() {
    // Provides access to MomentDao
    abstract fun momentDao(): MomentDao
}
