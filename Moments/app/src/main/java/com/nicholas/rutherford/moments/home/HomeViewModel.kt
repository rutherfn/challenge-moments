package com.nicholas.rutherford.moments.home

import androidx.lifecycle.ViewModel
import com.nicholas.rutherford.moments.repository.NoteRepository
import com.nicholas.rutherford.moments.room.NoteEntity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class HomeViewModel(
    private val noteRepository: NoteRepository,
    private val scope: CoroutineScope,
) : ViewModel() {

    private val _state = MutableStateFlow(HomeState())
    val state = _state.asStateFlow()

    init {
        scope.launch {
            val notes = noteRepository.getAllNotes()

            updateState(notes = notes)
        }
    }

    private fun updateState(notes: List<NoteEntity>) {

        _state.update { state ->
            state.copy(
                notes = notes
            )
        }
    }

    private fun onNewNoteButtonClicked() {

    }
}