package ru.kpfu.itis.springpractice.experiment.domain.usecase

import ru.kpfu.itis.springpractice.experiment.domain.model.Authorization
import ru.kpfu.itis.springpractice.experiment.domain.repositoryInterface.AuthRepositoryInterface

class AuthorizeUseCase(
    private val repo: AuthRepositoryInterface,
    private val authorization: Authorization
) {

    suspend fun authorize(email: String, password: String): Boolean {
        val isSuccessfullyAuthorized = repo.login(email, password)
        if (isSuccessfullyAuthorized) {
            authorization.isAuthorized = true
        }
        return isSuccessfullyAuthorized
    }
}