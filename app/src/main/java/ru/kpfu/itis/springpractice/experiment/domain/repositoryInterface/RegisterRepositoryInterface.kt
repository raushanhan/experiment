package ru.kpfu.itis.springpractice.experiment.domain.repositoryInterface

import ru.kpfu.itis.springpractice.experiment.domain.model.RegisterRequest
import ru.kpfu.itis.springpractice.experiment.domain.model.RegisterResponse

interface RegisterRepositoryInterface {

    suspend fun register(registerForm: RegisterRequest): RegisterResponse

    suspend fun checkIfEmailExists(email: String): Boolean
}