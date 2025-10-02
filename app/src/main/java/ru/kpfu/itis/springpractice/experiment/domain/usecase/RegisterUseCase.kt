package ru.kpfu.itis.springpractice.experiment.domain.usecase

import ru.kpfu.itis.springpractice.experiment.domain.model.RegisterRequest
import ru.kpfu.itis.springpractice.experiment.domain.model.RegisterResponse
import ru.kpfu.itis.springpractice.experiment.domain.repositoryInterface.RegisterRepositoryInterface

class RegisterUseCase(private val repo: RegisterRepositoryInterface) {

    suspend fun register(registerForm: RegisterRequest): RegisterResponse {
        return repo.register(registerForm)
    }

    suspend fun checkIfEmailExists(email: String): Boolean {
        return repo.checkIfEmailExists(email)
    }
}