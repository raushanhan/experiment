package ru.kpfu.itis.springpractice.experiment.data.remote.api

import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import ru.kpfu.itis.springpractice.experiment.domain.model.LoginRequest
import ru.kpfu.itis.springpractice.experiment.domain.model.LoginResponse
import ru.kpfu.itis.springpractice.experiment.domain.model.Note


interface AdventurerAppApi {

    @GET("notes")
    suspend fun getAuthorizedUserNotesList(): List<Note>

    @POST("auth/login")
    suspend fun login(@Body loginRequest: LoginRequest): Response<LoginResponse>
}

