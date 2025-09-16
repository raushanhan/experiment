package ru.kpfu.itis.springpractice.experiment.domain.model

data class NoteAddRequest(
    val title: String,
    val content: String,
    val latitude: Double,
    val longitude: Double,
    val imageUrl: String
)
