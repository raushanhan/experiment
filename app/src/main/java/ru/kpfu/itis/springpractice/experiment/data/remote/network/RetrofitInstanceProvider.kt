package ru.kpfu.itis.springpractice.experiment.data.remote.network

import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import ru.kpfu.itis.springpractice.experiment.BuildConfig.ADVENTURER_APP_BASE_URL
import ru.kpfu.itis.springpractice.experiment.data.remote.deserializer.LocalDateTimeAdapter
import ru.kpfu.itis.springpractice.experiment.data.tokenmanager.ITokenManager
import java.time.LocalDateTime


object RetrofitInstanceProvider {
    private lateinit var instance: Retrofit
    fun createInstance(tokenManager: ITokenManager): Retrofit {

        if (::instance.isInitialized) {
            return instance
        }

        val logging = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }


        val client = OkHttpClient.Builder()
            .addInterceptor(AuthInterceptor(tokenManager))
            .addInterceptor(logging)
            .build()

        val gson = GsonBuilder()
            .registerTypeAdapter(LocalDateTime::class.java, LocalDateTimeAdapter())
            .create()

        val retrofit = Retrofit.Builder()
            .baseUrl(ADVENTURER_APP_BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()

        instance = retrofit
        return retrofit
    }
}



