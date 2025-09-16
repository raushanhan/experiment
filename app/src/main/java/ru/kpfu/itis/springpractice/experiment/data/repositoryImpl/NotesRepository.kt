package ru.kpfu.itis.springpractice.experiment.data.repositoryImpl

import android.util.Log
import ru.kpfu.itis.springpractice.experiment.data.exception.UnsuccessfulNoteAdding
import ru.kpfu.itis.springpractice.experiment.data.remote.api.AdventurerAppApi
import ru.kpfu.itis.springpractice.experiment.domain.model.Note
import ru.kpfu.itis.springpractice.experiment.domain.model.NoteAddRequest
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

    override suspend fun addNote(note: NoteAddRequest): Note {
        val response = api.addNote(note)
        if (response.isSuccessful) {
            // TODO доделать фигню
            return response.body() as Note
        } else {
            println("NOTES REPOSITORY TEST TAG - error adding a note: ${response.message()}")
            throw UnsuccessfulNoteAdding(response.message())
        }
    }
}

