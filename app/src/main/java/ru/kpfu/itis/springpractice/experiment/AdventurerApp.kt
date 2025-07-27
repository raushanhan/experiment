package ru.kpfu.itis.springpractice.experiment

import android.app.Application
import retrofit2.Retrofit
import ru.kpfu.itis.springpractice.experiment.data.remote.api.AdventurerAppApi
import ru.kpfu.itis.springpractice.experiment.data.remote.network.RetrofitInstanceProvider
import ru.kpfu.itis.springpractice.experiment.repository.AuthRepository
import ru.kpfu.itis.springpractice.experiment.repository.NotesRepository
import ru.kpfu.itis.springpractice.experiment.util.SharedPrefsTokenManager

class AdventurerApp: Application() {
    lateinit var tokenManager: SharedPrefsTokenManager
    lateinit var retrofit: Retrofit
    lateinit var adventurerAppApi: AdventurerAppApi
    lateinit var authRepository: AuthRepository
    lateinit var notesRepository: NotesRepository

    override fun onCreate() {
        super.onCreate()

        tokenManager = SharedPrefsTokenManager(this)
        retrofit = RetrofitInstanceProvider.createInstance(tokenManager)
        adventurerAppApi = retrofit.create(AdventurerAppApi::class.java)
        authRepository = AuthRepository(adventurerAppApi, tokenManager)
        notesRepository = NotesRepository(adventurerAppApi)
    }
}