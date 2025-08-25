package ru.kpfu.itis.springpractice.experiment.domain.usecase

import ru.kpfu.itis.springpractice.experiment.data.remote.api.AdventurerAppApi
import ru.kpfu.itis.springpractice.experiment.domain.model.Note
import ru.kpfu.itis.springpractice.experiment.domain.repository.NotesRepositoryInterface

class LoadAuthorizedUserNotesUseCase(private val repo: NotesRepositoryInterface) {

    suspend fun loadNotes(): List<Note> {
        return repo.getNotes()
    }
}