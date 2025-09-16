package ru.kpfu.itis.springpractice.experiment.domain.usecase

import ru.kpfu.itis.springpractice.experiment.data.remote.api.AdventurerAppApi
import ru.kpfu.itis.springpractice.experiment.domain.model.Note
import ru.kpfu.itis.springpractice.experiment.domain.model.NoteAddRequest
import ru.kpfu.itis.springpractice.experiment.domain.repositoryInterface.NotesRepositoryInterface

class AddNoteUseCase(private val repo: NotesRepositoryInterface) {

    suspend fun addNote(note: NoteAddRequest): Note {
        return repo.addNote(note)
    }
}