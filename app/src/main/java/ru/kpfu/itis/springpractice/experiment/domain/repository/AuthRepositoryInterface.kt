package ru.kpfu.itis.springpractice.experiment.domain.repository

interface AuthRepositoryInterface {

    suspend fun login(email: String, password: String): Boolean
}