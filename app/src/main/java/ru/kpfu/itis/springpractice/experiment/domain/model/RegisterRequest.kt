package ru.kpfu.itis.springpractice.experiment.domain.model

data class RegisterRequest(
    val email: String,
    val password: String,
    val username: String
)
