package ru.kpfu.itis.springpractice.experiment.domain.usecase

import ru.kpfu.itis.springpractice.experiment.domain.repository.AuthRepositoryInterface

class AuthorizeUseCase(private val repo: AuthRepositoryInterface) {

    suspend fun authorize(email: String, password: String) {
        repo.login(email, password)
    }
}