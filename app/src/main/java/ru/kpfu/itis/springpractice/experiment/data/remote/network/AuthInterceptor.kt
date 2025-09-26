package ru.kpfu.itis.springpractice.experiment.data.remote.network

import okhttp3.Interceptor
import okhttp3.Response
import ru.kpfu.itis.springpractice.experiment.data.tokenmanager.ITokenManager

class AuthInterceptor(private val tokenManager: ITokenManager) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        println("LOG - I have intercepted with token ${tokenManager.getToken()}")
        val requestBuilder = chain.request().newBuilder()
        tokenManager.getToken()?.let {
            requestBuilder.addHeader("Authorization", "Bearer $it")
        }
        return chain.proceed(requestBuilder.build())
    }
}

