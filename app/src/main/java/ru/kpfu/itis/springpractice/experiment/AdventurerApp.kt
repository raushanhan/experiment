package ru.kpfu.itis.springpractice.experiment

import android.app.Application
import retrofit2.Retrofit
import ru.kpfu.itis.springpractice.experiment.data.remote.GlideImageLoader
import ru.kpfu.itis.springpractice.experiment.data.remote.api.AdventurerAppApi
import ru.kpfu.itis.springpractice.experiment.data.remote.network.RetrofitInstanceProvider
import ru.kpfu.itis.springpractice.experiment.data.repositoryImpl.AuthRepository
import ru.kpfu.itis.springpractice.experiment.data.repositoryImpl.ImagesRepository
import ru.kpfu.itis.springpractice.experiment.data.repositoryImpl.NotesRepository
import ru.kpfu.itis.springpractice.experiment.data.tokenmanager.SharedPrefsTokenManager
import ru.kpfu.itis.springpractice.experiment.domain.repositoryInterface.AuthRepositoryInterface
import ru.kpfu.itis.springpractice.experiment.domain.repositoryInterface.ImagesRepositoryInterface
import ru.kpfu.itis.springpractice.experiment.domain.repositoryInterface.NotesRepositoryInterface
import ru.kpfu.itis.springpractice.experiment.domain.usecase.AddNoteUseCase
import ru.kpfu.itis.springpractice.experiment.domain.usecase.AuthorizeUseCase
import ru.kpfu.itis.springpractice.experiment.domain.usecase.DeleteNoteUseCase
import ru.kpfu.itis.springpractice.experiment.domain.usecase.LoadAuthorizedUserNotesUseCase
import ru.kpfu.itis.springpractice.experiment.domain.usecase.PullNoteUseCase
import ru.kpfu.itis.springpractice.experiment.domain.usecase.UploadImageUseCase
import ru.kpfu.itis.springpractice.experiment.presentation.util.ImageLoaderInterface

class AdventurerApp: Application() {
    lateinit var tokenManager: SharedPrefsTokenManager
    lateinit var retrofit: Retrofit
    lateinit var adventurerAppApi: AdventurerAppApi
    lateinit var authRepository: AuthRepositoryInterface
    lateinit var notesRepository: NotesRepositoryInterface
    lateinit var imagesRepository: ImagesRepositoryInterface
    lateinit var loadAuthorizedUserNotesUseCase: LoadAuthorizedUserNotesUseCase
    lateinit var authorizeUseCase: AuthorizeUseCase
    lateinit var deleteNoteUseCase: DeleteNoteUseCase
    lateinit var addNoteUseCase: AddNoteUseCase
    lateinit var uploadImageUseCase: UploadImageUseCase
    lateinit var pullNoteUseCase: PullNoteUseCase
    lateinit var imageLoader: ImageLoaderInterface

    override fun onCreate() {
        super.onCreate()

        tokenManager = SharedPrefsTokenManager(this)
        retrofit = RetrofitInstanceProvider.createInstance(tokenManager)
        adventurerAppApi = retrofit.create(AdventurerAppApi::class.java)
        authRepository = AuthRepository(adventurerAppApi, tokenManager)
        notesRepository = NotesRepository(adventurerAppApi)
        imagesRepository = ImagesRepository(adventurerAppApi)
        loadAuthorizedUserNotesUseCase = LoadAuthorizedUserNotesUseCase(notesRepository)
        authorizeUseCase = AuthorizeUseCase(authRepository)
        deleteNoteUseCase = DeleteNoteUseCase(notesRepository)
        addNoteUseCase = AddNoteUseCase(notesRepository)
        uploadImageUseCase = UploadImageUseCase(imagesRepository)
        pullNoteUseCase = PullNoteUseCase(notesRepository)
        imageLoader = GlideImageLoader()
    }
}