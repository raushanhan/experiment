package ru.kpfu.itis.springpractice.experiment.presentation.viewmodelfactory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import ru.kpfu.itis.springpractice.experiment.data.repositoryImpl.ImagesRepository
import ru.kpfu.itis.springpractice.experiment.domain.usecase.AddNoteUseCase
import ru.kpfu.itis.springpractice.experiment.domain.usecase.UploadImageUseCase
import ru.kpfu.itis.springpractice.experiment.presentation.viewmodel.NoteAddingViewModel

/**
 * ВРЕМЕННЫЙ КЛАСС, С hilt БУДЕТ ДРУГАЯ СИСТЕМА
 */
class NoteAddingViewModelFactory(
    val addNoteUseCase: AddNoteUseCase,
    val uploadImageUseCase: UploadImageUseCase
) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(NoteAddingViewModel::class.java)) {
            return NoteAddingViewModel(
                addNoteUseCase,
                uploadImageUseCase
            ) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class: ${modelClass.name}")
    }
}
