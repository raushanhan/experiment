package ru.kpfu.itis.springpractice.experiment.domain.repository

import ru.kpfu.itis.springpractice.experiment.domain.model.Note

interface NotesRepositoryInterface {

    suspend fun getNotes(): List<Note>

    suspend fun deleteNote(id: Int): Boolean

    suspend fun addNote(note: Note): Boolean
}