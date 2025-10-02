package ru.kpfu.itis.springpractice.experiment.presentation.viewmodelfactory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import ru.kpfu.itis.springpractice.experiment.domain.usecase.GetUserInfoUseCase
import ru.kpfu.itis.springpractice.experiment.domain.usecase.LogOutUseCase
import ru.kpfu.itis.springpractice.experiment.presentation.viewmodel.ProfileViewModel

class ProfileViewModelFactory(
    private val getUserInfoUseCase: GetUserInfoUseCase,
    private val logOutUseCase: LogOutUseCase
) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T: ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ProfileViewModel::class.java)) {
            return ProfileViewModel(
                getUserInfoUseCase,
                logOutUseCase
            ) as T
        }
        throw IllegalArgumentException("Unknown View model class: ${modelClass.name}")
    }
}