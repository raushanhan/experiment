package ru.kpfu.itis.springpractice.experiment.domain.model

import java.time.LocalDateTime

data class NoteAddRequest(
    val title: String,
    val content: String,
    val latitude: Double,
    val longitude: Double,
    val imageUrl: String,
    val createdAt: LocalDateTime
)
