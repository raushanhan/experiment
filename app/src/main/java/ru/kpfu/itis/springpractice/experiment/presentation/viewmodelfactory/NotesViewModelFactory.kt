package ru.kpfu.itis.springpractice.experiment.presentation.viewmodelfactory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import ru.kpfu.itis.springpractice.experiment.domain.usecase.DeleteNoteUseCase
import ru.kpfu.itis.springpractice.experiment.domain.usecase.LoadAuthorizedUserNotesUseCase
import ru.kpfu.itis.springpractice.experiment.presentation.util.ImageLoaderInterface
import ru.kpfu.itis.springpractice.experiment.presentation.viewmodel.NotesViewModel

/**
 * ВРЕМЕННЫЙ КЛАСС, С hilt БУДЕТ ДРУГАЯ СИСТЕМА
 */
class NotesViewModelFactory(
    private val loadAuthorizedUserNotesUseCase: LoadAuthorizedUserNotesUseCase,
    private val deleteNoteUseCase: DeleteNoteUseCase,
    private val imageLoader: ImageLoaderInterface,
) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(NotesViewModel::class.java)) {
            return NotesViewModel(
                loadAuthorizedUserNotesUseCase,
                deleteNoteUseCase,
                imageLoader
            ) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class: ${modelClass.name}")
    }
}