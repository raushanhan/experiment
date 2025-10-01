package ru.kpfu.itis.springpractice.experiment.domain.model

/**
 * Data class that captures user information for logged in users retrieved from LoginRepository
 */
data class User(
    val id: Long,
    val username: String,
    val email: String
)