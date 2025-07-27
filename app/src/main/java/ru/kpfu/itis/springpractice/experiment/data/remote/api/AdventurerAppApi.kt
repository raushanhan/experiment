package ru.kpfu.itis.springpractice.experiment.data.remote.api

import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import ru.kpfu.itis.springpractice.experiment.model.LoginRequest
import ru.kpfu.itis.springpractice.experiment.model.LoginResponse
import ru.kpfu.itis.springpractice.experiment.model.Note


interface AdventurerAppApi {

    @GET("notes")
    suspend fun getNotesListAuthorized(): List<Note>

    @POST("auth/login")
    suspend fun login(@Body loginRequest: LoginRequest): Response<LoginResponse>
}

