package ru.kpfu.itis.springpractice.experiment.domain.usecase

import ru.kpfu.itis.springpractice.experiment.domain.model.Note
import ru.kpfu.itis.springpractice.experiment.domain.repositoryInterface.NotesRepositoryInterface

class PullNoteUseCase(private val repo: NotesRepositoryInterface) {

    suspend fun pullNote(noteId: Long): Note {
        return repo.getNote(noteId)
    }
}