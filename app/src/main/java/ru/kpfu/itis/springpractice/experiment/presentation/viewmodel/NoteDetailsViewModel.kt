package ru.kpfu.itis.springpractice.experiment.presentation.viewmodel

import android.widget.ImageView
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import ru.kpfu.itis.springpractice.experiment.domain.exception.UnsuccessfulNotePulling
import ru.kpfu.itis.springpractice.experiment.domain.model.Note
import ru.kpfu.itis.springpractice.experiment.domain.usecase.PullNoteUseCase
import ru.kpfu.itis.springpractice.experiment.presentation.util.ImageLoaderInterface

class NoteDetailsViewModel(
    private val pullNoteUseCase: PullNoteUseCase,
    private val imageLoader: ImageLoaderInterface
) : ViewModel() {

    private val _note = MutableLiveData<Note>()
    val note: LiveData<Note> = _note

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    private val _error = MutableLiveData<String?>()
    val error: LiveData<String?> = _error


    fun loadNote(noteId: Long) {
        viewModelScope.launch {
            try {
                _isLoading.value = true
                val note = pullNoteUseCase.pullNote(noteId)
                println("NOTE DETAILS VIEW MODEL TEST TAG - got note $note")
                _note.value = note
            } catch (e: UnsuccessfulNotePulling) {
                println("NOTE DETAILS VIEW MODEL TEST TAG - error pulling note: ${e.message}")
                _error.value = e.message
            } finally {
                _isLoading.value = false
            }
        }
    }

    fun loadImage(path: String, imageView: ImageView) {
        imageLoader.loadImageIntoImageView(path, imageView)
    }

}