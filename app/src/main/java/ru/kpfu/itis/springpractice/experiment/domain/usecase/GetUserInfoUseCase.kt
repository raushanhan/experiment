package ru.kpfu.itis.springpractice.experiment.domain.usecase

import ru.kpfu.itis.springpractice.experiment.data.repositoryImpl.UserRepository
import ru.kpfu.itis.springpractice.experiment.domain.model.User
import ru.kpfu.itis.springpractice.experiment.domain.repositoryInterface.UserRepositoryInterface

class GetUserInfoUseCase(
    private val userRepository: UserRepositoryInterface
) {

    suspend fun getUserInfo(): User {
        return userRepository.getUser()
    }
}