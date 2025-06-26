package com.nicholas.rutherford.moments.repository

import com.nicholas.rutherford.moments.data.CategoryTag
import com.nicholas.rutherford.moments.data.Note
import com.nicholas.rutherford.moments.room.NoteEntity

interface NoteRepository {
    suspend fun insertNote(note: NoteEntity)
    suspend fun getAllNotes(): List<NoteEntity>
    suspend fun getNotesByCategory(tag: CategoryTag): List<NoteEntity>
    suspend fun deleteNote(note: NoteEntity)
}