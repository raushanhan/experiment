package ru.kpfu.itis.springpractice.experiment.data.repositoryImpl

import android.util.Log
import ru.kpfu.itis.springpractice.experiment.data.remote.api.AdventurerAppApi
import ru.kpfu.itis.springpractice.experiment.data.util.ImagePreparator
import ru.kpfu.itis.springpractice.experiment.domain.repositoryInterface.ImagesRepositoryInterface
import java.io.File

class ImagesRepository(
    private val api: AdventurerAppApi
) : ImagesRepositoryInterface {

    override suspend fun uploadImage(file: File) {
        val body = ImagePreparator.prepareFilePart(file)

        val response = api.uploadImage(body)
        if (response.isSuccessful) {
            println("IMAGES REPOSITORY TEST TAG - successfully uploaded file ${file.name}")
//            Log.d("API", "Upload success")
        } else {
            println("IMAGES REPOSITORY TEST TAG - failed to upload file ${file.name}, ${response.code()}")
//            Log.e("API", "Upload failed: ${response.code()}")
        }
    }
}