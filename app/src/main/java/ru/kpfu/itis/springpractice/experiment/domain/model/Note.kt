package ru.kpfu.itis.springpractice.experiment.domain.model

import java.time.LocalDateTime

data class Note (
    val id: Long,
    val imageUrl: String,
    val title: String,
    val content: String,
    val createdAt: LocalDateTime,
    val updatedAt: LocalDateTime,
    val latitude: Double,
    val longitude: Double
)

