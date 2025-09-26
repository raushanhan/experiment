package ru.kpfu.itis.springpractice.experiment.presentation.viewmodelfactory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import ru.kpfu.itis.springpractice.experiment.domain.usecase.PullNoteUseCase
import ru.kpfu.itis.springpractice.experiment.presentation.util.ImageLoaderInterface
import ru.kpfu.itis.springpractice.experiment.presentation.viewmodel.NoteDetailsViewModel

class NoteDetailsViewModelFactory(
    private val pullNoteUseCase: PullNoteUseCase,
    private val imageLoader: ImageLoaderInterface
) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T: ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(NoteDetailsViewModel::class.java)) {
            return NoteDetailsViewModel(
                pullNoteUseCase,
                imageLoader
            ) as T
        }
        throw IllegalArgumentException("Unknown View model class: ${modelClass.name}")
    }
}