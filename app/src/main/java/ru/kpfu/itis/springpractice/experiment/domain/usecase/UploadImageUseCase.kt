package ru.kpfu.itis.springpractice.experiment.domain.usecase

import ru.kpfu.itis.springpractice.experiment.domain.repositoryInterface.ImagesRepositoryInterface
import java.io.File

class UploadImageUseCase(
    private val repo: ImagesRepositoryInterface
) {

    suspend fun uploadImage(file: File) {
        repo.uploadImage(file)
    }
}