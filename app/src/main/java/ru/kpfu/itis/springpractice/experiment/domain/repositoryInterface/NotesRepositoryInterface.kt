package ru.kpfu.itis.springpractice.experiment.domain.repositoryInterface

import ru.kpfu.itis.springpractice.experiment.domain.model.Note

interface NotesRepositoryInterface {

    suspend fun getNotes(): List<Note>

    suspend fun deleteNote(id: Long): Boolean

    suspend fun addNote(note: Note): Boolean
}