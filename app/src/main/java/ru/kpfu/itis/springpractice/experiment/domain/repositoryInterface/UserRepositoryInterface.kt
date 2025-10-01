package ru.kpfu.itis.springpractice.experiment.domain.repositoryInterface

import ru.kpfu.itis.springpractice.experiment.domain.model.User

interface UserRepositoryInterface {

    suspend fun getUser(): User
}