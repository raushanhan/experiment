package ru.kpfu.itis.springpractice.experiment.data.remote.network

import android.content.SharedPreferences
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import ru.kpfu.itis.springpractice.experiment.BuildConfig.ADVENTURER_APP_BASE_URL
import ru.kpfu.itis.springpractice.experiment.data.remote.api.AdventurerAppApi
import ru.kpfu.itis.springpractice.experiment.domain.tokenmanager.ITokenManager

object RetrofitInstanceProvider {
    private lateinit var instance: Retrofit
    fun createInstance(tokenManager: ITokenManager): Retrofit {

        if (::instance.isInitialized) {
            return instance
        }

        val client = OkHttpClient.Builder()
            .addInterceptor(AuthInterceptor(tokenManager))
            .build()

        val retrofit = Retrofit.Builder()
            .baseUrl(ADVENTURER_APP_BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        instance = retrofit
        return retrofit
    }
}



