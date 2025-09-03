package ru.kpfu.itis.springpractice.experiment.domain.usecase

import ru.kpfu.itis.springpractice.experiment.domain.repositoryInterface.NotesRepositoryInterface

class DeleteNoteUseCase(private val repo: NotesRepositoryInterface) {

    suspend fun delete(id: Long): Boolean {
        return repo.deleteNote(id)
    }
}