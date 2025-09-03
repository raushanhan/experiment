package ru.kpfu.itis.springpractice.experiment.data.repositoryImpl

import ru.kpfu.itis.springpractice.experiment.data.remote.api.AdventurerAppApi
import ru.kpfu.itis.springpractice.experiment.domain.model.Note
import ru.kpfu.itis.springpractice.experiment.domain.repositoryInterface.NotesRepositoryInterface

class NotesRepository(
    private val api: AdventurerAppApi
) : NotesRepositoryInterface {

    override suspend fun getNotes(): List<Note> {
        return api.getNotesList()
    }

    override suspend fun deleteNote(id: Long): Boolean {
        return api.deleteNote(id)
    }

    override suspend fun addNote(note: Note): Boolean {
        TODO("Not yet implemented")
    }
}

