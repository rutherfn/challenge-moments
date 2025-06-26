package com.nicholas.rutherford.moments.repository

import com.nicholas.rutherford.moments.data.CategoryTag
import com.nicholas.rutherford.moments.room.NoteDao
import com.nicholas.rutherford.moments.room.NoteEntity

class NoteRepositoryImpl(private val noteDao: NoteDao) : NoteRepository {

    override suspend fun insertNote(note: NoteEntity) {
        noteDao.insert(note)
    }

    override suspend fun getAllNotes(): List<NoteEntity> {
        return noteDao.getAllNotes()
    }

    override suspend fun getNotesByCategory(tag: CategoryTag): List<NoteEntity> {
        return noteDao.getNotesByCategory(tag.typeIdentifier)
    }

    override suspend fun deleteNote(note: NoteEntity) {
        noteDao.delete(note)
    }
}
