package ru.kpfu.itis.springpractice.experiment.domain.usecase

import ru.kpfu.itis.springpractice.experiment.domain.exception.AuthorizationException
import ru.kpfu.itis.springpractice.experiment.domain.repositoryInterface.UserRepositoryInterface

class CheckAuthorizationUseCase(private val userRepository: UserRepositoryInterface) {

    suspend fun checkAuth(): Boolean {
        return try {
            userRepository.getUser()
            true
        } catch (e: AuthorizationException) {
            false
        }
    }
}