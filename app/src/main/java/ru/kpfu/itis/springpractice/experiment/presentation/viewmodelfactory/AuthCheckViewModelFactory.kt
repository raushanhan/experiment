package ru.kpfu.itis.springpractice.experiment.presentation.viewmodelfactory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import ru.kpfu.itis.springpractice.experiment.domain.model.Authorization
import ru.kpfu.itis.springpractice.experiment.domain.usecase.CheckAuthorizationUseCase
import ru.kpfu.itis.springpractice.experiment.presentation.viewmodel.AuthCheckViewModel

class AuthCheckViewModelFactory (
    private val checkAuthorizationUseCase: CheckAuthorizationUseCase
) : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T: ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(AuthCheckViewModel::class.java)) {
            return AuthCheckViewModel(
                checkAuthorizationUseCase = checkAuthorizationUseCase
            ) as T
        }
        throw IllegalArgumentException("Unknown View model class: ${modelClass.name}")
    }
}