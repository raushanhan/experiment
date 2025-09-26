package ru.kpfu.itis.springpractice.experiment.domain.repositoryInterface

import java.io.File

interface ImagesRepositoryInterface {

    suspend fun uploadImage(file: File)
}