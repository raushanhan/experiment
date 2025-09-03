package ru.kpfu.itis.springpractice.experiment.presentation.viewmodelfactory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import ru.kpfu.itis.springpractice.experiment.domain.usecase.AuthorizeUseCase
import ru.kpfu.itis.springpractice.experiment.domain.usecase.DeleteNoteUseCase
import ru.kpfu.itis.springpractice.experiment.domain.usecase.LoadAuthorizedUserNotesUseCase
import ru.kpfu.itis.springpractice.experiment.presentation.viewmodel.ExperimentViewModel

/**
 * ВРЕМЕННЫЙ КЛАСС, С hilt БУДЕТ ДРУГАЯ СИСТЕМА
 */
class ExperimentViewModelFactory(
    private val authorizeUseCase: AuthorizeUseCase,
    private val loadAuthorizedUserNotesUseCase: LoadAuthorizedUserNotesUseCase,
    private val deleteNoteUseCase: DeleteNoteUseCase
) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ExperimentViewModel::class.java)) {
            return ExperimentViewModel(authorizeUseCase, loadAuthorizedUserNotesUseCase, deleteNoteUseCase) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class: ${modelClass.name}")
    }
}