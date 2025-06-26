package com.nicholas.rutherford.moments.room

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.nicholas.rutherford.moments.data.CategoryTag
import com.nicholas.rutherford.moments.data.converter.CategoryTagConverter
import java.util.UUID

@Entity(tableName = "notes")
@TypeConverters(CategoryTagConverter::class)
data class NoteEntity(
    @PrimaryKey val id: String = UUID.randomUUID().toString(),
    val title: String,
    val categoryTag: CategoryTag
)