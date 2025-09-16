package ru.kpfu.itis.springpractice.experiment.domain.repositoryInterface

import ru.kpfu.itis.springpractice.experiment.data.remote.api.AdventurerAppApi
import java.io.File

interface ImagesRepositoryInterface {

    suspend fun uploadImage(file: File)
}