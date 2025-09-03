package ru.kpfu.itis.springpractice.experiment.data.remote.api

import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query
import ru.kpfu.itis.springpractice.experiment.domain.model.LoginRequest
import ru.kpfu.itis.springpractice.experiment.domain.model.LoginResponse
import ru.kpfu.itis.springpractice.experiment.domain.model.Note


interface AdventurerAppApi {

    @GET("notes")
    suspend fun getNotesList(): List<Note>

    @DELETE("notes")
    suspend fun deleteNote(@Query("id") id: Long): Boolean

    @POST("auth/login")
    suspend fun login(@Body loginRequest: LoginRequest): Response<LoginResponse>
}

