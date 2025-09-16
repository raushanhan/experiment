package ru.kpfu.itis.springpractice.experiment.presentation.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import ru.kpfu.itis.springpractice.experiment.data.exception.UnsuccessfulNoteAdding
import ru.kpfu.itis.springpractice.experiment.domain.model.Note
import ru.kpfu.itis.springpractice.experiment.domain.model.NoteAddRequest
import ru.kpfu.itis.springpractice.experiment.domain.usecase.AddNoteUseCase
import ru.kpfu.itis.springpractice.experiment.domain.usecase.UploadImageUseCase
import ru.kpfu.itis.springpractice.experiment.presentation.ui.locationManager.LocationManager
import java.io.File

class NoteAddingViewModel(
    private val useCase: AddNoteUseCase,
    private val uploadImageUseCase: UploadImageUseCase
) : ViewModel() {

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading = _isLoading

    private val _addedNote = MutableLiveData<Note?>()
    val addedNote = _addedNote

    private val _addingError = MutableLiveData<String?>()
    val addingError = _addingError

    fun addNote(note: Note, locationManager: LocationManager) {
        println("NOTE ADDING VIEW MODEL TEST TAG - received note(${note})")
        viewModelScope.launch {
            val location = locationManager.getLocation()
            println("NOTE ADDING VIEW MODEL TEST TAG - get location: $location")
            _isLoading.value = true
            try {
                if (location != null) {
                    if (note.imageUrl.isNotEmpty()) {
                        uploadImageUseCase.uploadImage(File(note.imageUrl))
                    }
                    val noteRequest = NoteAddRequest(
                        title = note.title,
                        content = note.content,
                        latitude = location.latitude,
                        longitude = location.longitude,
                        imageUrl = note.imageUrl
                    )
                    println("NOTE ADDING VIEW MODEL TEST TAG - create note request (${noteRequest})")
                    val result = useCase.addNote(noteRequest)
                    println("NOTE ADDING VIEW MODEL TEST TAG - successfully added note(${result})")
                    _addedNote.value = result
                }
            } catch (e: UnsuccessfulNoteAdding) {
                println("NOTE ADDING VIEW MODEL TEST TAG - note adding unsuccess(${e.message})")
                _addingError.value = e.message
            }
        }
    }
}