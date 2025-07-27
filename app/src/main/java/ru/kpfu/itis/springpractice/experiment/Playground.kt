package ru.kpfu.itis.springpractice.experiment

import kotlinx.coroutines.runBlocking
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import ru.kpfu.itis.springpractice.experiment.data.remote.api.AdventurerAppApi
import ru.kpfu.itis.springpractice.experiment.data.remote.network.AuthInterceptor
import ru.kpfu.itis.springpractice.experiment.repository.AuthRepository
import ru.kpfu.itis.springpractice.experiment.util.SpecialTM
import ru.kpfu.itis.springpractice.experiment.repository.NotesRepository

fun main() = runBlocking {

    val tokenManager = SpecialTM()
    val client = OkHttpClient.Builder()
        .addInterceptor(AuthInterceptor(tokenManager))
        .build()

    val retrofit = Retrofit.Builder()
        .baseUrl("http://localhost:8080/api/")
        .addConverterFactory(GsonConverterFactory.create())
        .client(client)
        .build()

    val api = retrofit.create(AdventurerAppApi::class.java)
    val authRepo = AuthRepository(api, tokenManager)
    val notesRepo = NotesRepository(api)

    println("before login")
    try {
        val res = authRepo.login("email@email.com", "rrr")
        println("login result: $res")
    } catch (e: Exception) {
        println("Error during login: $e")
    }
    println("after login")

    println("before notes fetch")
    try {
        val notes = api.getNotesListAuthorized()
        println(notes)
    } catch (e: Exception) {
        println("Error during notes fetch: $e")
    }
    val notes = api.getNotesListAuthorized()
    println(notes)
    println("after notes fetch")

}