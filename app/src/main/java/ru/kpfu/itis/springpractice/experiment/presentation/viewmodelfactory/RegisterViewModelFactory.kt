package ru.kpfu.itis.springpractice.experiment.presentation.viewmodelfactory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import ru.kpfu.itis.springpractice.experiment.domain.usecase.AuthorizeUseCase
import ru.kpfu.itis.springpractice.experiment.domain.usecase.RegisterUseCase
import ru.kpfu.itis.springpractice.experiment.presentation.viewmodel.NoteDetailsViewModel
import ru.kpfu.itis.springpractice.experiment.presentation.viewmodel.RegisterViewModel

class RegisterViewModelFactory(
    private var registerUseCase: RegisterUseCase,
    private var authorizeUseCase: AuthorizeUseCase
) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T: ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(RegisterViewModel::class.java)) {
            return RegisterViewModel(
                registerUseCase = registerUseCase,
                authorizeUseCase = authorizeUseCase
            ) as T
        }
        throw IllegalArgumentException("Unknown View model class: ${modelClass.name}")
    }
}