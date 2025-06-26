package com.nicholas.rutherford.moments.home

import com.nicholas.rutherford.moments.room.NoteEntity

data class HomeState(
    val notes: List<NoteEntity> = emptyList(),
)