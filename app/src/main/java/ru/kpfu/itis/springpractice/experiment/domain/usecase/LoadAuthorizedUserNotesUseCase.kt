package ru.kpfu.itis.springpractice.experiment.domain.usecase

import ru.kpfu.itis.springpractice.experiment.domain.model.Note
import ru.kpfu.itis.springpractice.experiment.domain.repositoryInterface.NotesRepositoryInterface

class LoadAuthorizedUserNotesUseCase(private val repo: NotesRepositoryInterface) {

    suspend fun loadNotes(): List<Note> {
        return repo.getNotes()
    }
}