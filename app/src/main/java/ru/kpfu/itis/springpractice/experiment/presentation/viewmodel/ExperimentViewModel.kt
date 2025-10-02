package ru.kpfu.itis.springpractice.experiment.presentation.viewmodel

import android.widget.ImageView
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import ru.kpfu.itis.springpractice.experiment.domain.model.Note
import ru.kpfu.itis.springpractice.experiment.domain.usecase.DeleteNoteUseCase
import ru.kpfu.itis.springpractice.experiment.domain.usecase.LoadAuthorizedUserNotesUseCase
import ru.kpfu.itis.springpractice.experiment.presentation.util.ImageLoaderInterface

class ExperimentViewModel(
    private val loadAuthorizedUserNotesUseCase: LoadAuthorizedUserNotesUseCase,
    private val deleteNoteUseCase: DeleteNoteUseCase,
    private val imageLoader: ImageLoaderInterface,
) : ViewModel() {

    private val _notes = MutableLiveData<List<Note>>()
    val notes: LiveData<List<Note>> = _notes

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    private val _error = MutableLiveData<String?>()
    val error: LiveData<String?> = _error

    private val _isDeleting = MutableLiveData<Boolean>()
    val isDeleting: LiveData<Boolean> = _isDeleting

    private val _deletingError = MutableLiveData<String?>()
    val deletingError: LiveData<String?> = _deletingError

    private val _isDeletedSuccessfully = MutableLiveData<Boolean>()
    val isDeletedSuccessfully: LiveData<Boolean> = _isDeletedSuccessfully

    fun loadData() {
        viewModelScope.launch {
            try {
                _isLoading.value = true
                val items = loadAuthorizedUserNotesUseCase.loadNotes()
                println("NOTES VIEW MODEL TEST TAG - $items")
                _notes.value = items
            } catch (e: Exception) {
                println("NOTES VIEW MODEL TEST TAG - error: ${e.message}")
                _error.value = e.message
            } finally {
                _isLoading.value = false
            }
        }
    }

    fun deleteNote(id: Long) {
        viewModelScope.launch {
            try {
                _isDeleting.value = true
                val result = deleteNoteUseCase.delete(id)
                if (result) {
                    println("NOTES VIEW MODEL TEST TAG - successfully deleted note(id=$id)")
                    val items = loadAuthorizedUserNotesUseCase.loadNotes()
                    _notes.value = items
                    _isDeletedSuccessfully.value = true
                } else {
                    println("NOTES VIEW MODEL TEST TAG - did not delete note(id=$id)")
                }
            } catch (e: Exception) {
                println("NOTES VIEW MODEL TEST TAG - deleting error: ${e.message}")
                _deletingError.value = e.message
            } finally {
                _isDeleting.value = false
                _isDeletedSuccessfully.value = false
            }
        }
    }

    fun loadImages(path: String, imageView: ImageView) {
        imageLoader.loadImageIntoImageView(path, imageView)
    }
}