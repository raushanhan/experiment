package ru.kpfu.itis.springpractice.experiment.data.remote.api

import okhttp3.MultipartBody
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part
import retrofit2.http.Path
import retrofit2.http.Query
import ru.kpfu.itis.springpractice.experiment.domain.model.User
import ru.kpfu.itis.springpractice.experiment.domain.model.LoginRequest
import ru.kpfu.itis.springpractice.experiment.domain.model.LoginResponse
import ru.kpfu.itis.springpractice.experiment.domain.model.Note
import ru.kpfu.itis.springpractice.experiment.domain.model.NoteAddRequest
import ru.kpfu.itis.springpractice.experiment.domain.model.RegisterRequest
import ru.kpfu.itis.springpractice.experiment.domain.model.RegisterResponse


interface AdventurerAppApi {

    @GET("notes")
    suspend fun getNotesList(): Response<List<Note>>

    @DELETE("notes")
    suspend fun deleteNote(@Query("id") id: Long): Response<Boolean>

    @POST("auth/login")
    suspend fun login(@Body loginRequest: LoginRequest): Response<LoginResponse>

    @POST("auth/register")
    suspend fun register(@Body request: RegisterRequest): Response<RegisterResponse>

    @POST(value = "notes")
    suspend fun addNote(@Body note: NoteAddRequest): Response<Note>

    @Multipart
    @POST("files/upload")
    suspend fun uploadImage(
        @Part image: MultipartBody.Part
    ): Response<Unit>

    @GET("notes/{id}")
    suspend fun getNoteById(@Path("id") id: Long): Response<Note>

    @GET("user/me")
    suspend fun getUserInfo(): Response<User>

    @GET("user/isPresent/{email}")
    suspend fun checkIfEmailExists(@Path("email") email: String): Response<Boolean>
}

