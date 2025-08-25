package ru.kpfu.itis.springpractice.experiment

import android.app.Application
import retrofit2.Retrofit
import ru.kpfu.itis.springpractice.experiment.data.remote.api.AdventurerAppApi
import ru.kpfu.itis.springpractice.experiment.data.remote.network.RetrofitInstanceProvider
import ru.kpfu.itis.springpractice.experiment.data.repositoryinterface.AuthRepository
import ru.kpfu.itis.springpractice.experiment.data.repositoryinterface.NotesRepository
import ru.kpfu.itis.springpractice.experiment.data.tokenmanager.SharedPrefsTokenManager
import ru.kpfu.itis.springpractice.experiment.domain.usecase.AuthorizeUseCase
import ru.kpfu.itis.springpractice.experiment.domain.usecase.LoadAuthorizedUserNotesUseCase

class AdventurerApp: Application() {
    lateinit var tokenManager: SharedPrefsTokenManager
    lateinit var retrofit: Retrofit
    lateinit var adventurerAppApi: AdventurerAppApi
    lateinit var authRepository: AuthRepository
    lateinit var notesRepository: NotesRepository
    lateinit var loadAuthorizedUserNotesUseCase: LoadAuthorizedUserNotesUseCase
    lateinit var authorizeUseCase: AuthorizeUseCase

    override fun onCreate() {
        super.onCreate()

        tokenManager = SharedPrefsTokenManager(this)
        retrofit = RetrofitInstanceProvider.createInstance(tokenManager)
        adventurerAppApi = retrofit.create(AdventurerAppApi::class.java)
        authRepository = AuthRepository(adventurerAppApi, tokenManager)
        notesRepository = NotesRepository(adventurerAppApi)
        loadAuthorizedUserNotesUseCase = LoadAuthorizedUserNotesUseCase(notesRepository) // тут возможно стоит убрать
                                                                        // задание репозитория через конструктор
        authorizeUseCase = AuthorizeUseCase(authRepository)
    }
}