package ru.kpfu.itis.springpractice.experiment.domain.repositoryInterface

interface AuthRepositoryInterface {

    suspend fun login(email: String, password: String): Boolean
}