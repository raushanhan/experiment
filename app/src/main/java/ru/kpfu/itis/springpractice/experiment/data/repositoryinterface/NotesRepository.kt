package ru.kpfu.itis.springpractice.experiment.data.repositoryinterface

import ru.kpfu.itis.springpractice.experiment.data.remote.api.AdventurerAppApi
import ru.kpfu.itis.springpractice.experiment.domain.model.Note
import ru.kpfu.itis.springpractice.experiment.domain.repository.NotesRepositoryInterface

class NotesRepository(
    private val api: AdventurerAppApi
) : NotesRepositoryInterface {

    override suspend fun getNotes(): List<Note> {
        return api.getAuthorizedUserNotesList()
    }

    override suspend fun deleteNote(id: Int): Boolean {
        TODO("Not yet implemented")
    }

    override suspend fun addNote(note: Note): Boolean {
        TODO("Not yet implemented")
    }
}

