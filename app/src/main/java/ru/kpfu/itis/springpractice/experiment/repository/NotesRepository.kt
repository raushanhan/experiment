package ru.kpfu.itis.springpractice.experiment.repository

import ru.kpfu.itis.springpractice.experiment.data.remote.api.AdventurerAppApi
import ru.kpfu.itis.springpractice.experiment.model.Note

class NotesRepository(
    private val api: AdventurerAppApi
) {

    suspend fun getNotes(): List<Note> {
        return api.getNotesListAuthorized()
    }
}

