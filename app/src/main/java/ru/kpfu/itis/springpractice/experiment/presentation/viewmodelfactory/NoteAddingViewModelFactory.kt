package ru.kpfu.itis.springpractice.experiment.presentation.viewmodelfactory;

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

/**
 * ВРЕМЕННЫЙ КЛАСС, С hilt БУДЕТ ДРУГАЯ СИСТЕМА
 */
class NoteAddingViewModelFactory() : ViewModelProvider.Factory {

    @Suppress
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return super.create(modelClass)
    }
}
