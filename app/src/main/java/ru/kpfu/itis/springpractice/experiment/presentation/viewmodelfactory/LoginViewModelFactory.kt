package ru.kpfu.itis.springpractice.experiment.presentation.viewmodelfactory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import ru.kpfu.itis.springpractice.experiment.domain.usecase.AuthorizeUseCase
import ru.kpfu.itis.springpractice.experiment.presentation.viewmodel.LoginViewModel
import ru.kpfu.itis.springpractice.experiment.presentation.viewmodel.RegisterViewModel

class LoginViewModelFactory(
    private val authorizeUseCase: AuthorizeUseCase
) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T: ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(LoginViewModel::class.java)) {
            return LoginViewModel(
                authorizeUseCase = authorizeUseCase
            ) as T
        }
        throw IllegalArgumentException("Unknown View model class: ${modelClass.name}")
    }
}