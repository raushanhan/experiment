package ru.kpfu.itis.springpractice.experiment.data.remote.network

import okhttp3.Interceptor
import okhttp3.Response
import ru.kpfu.itis.springpractice.experiment.data.tokenmanager.ITokenManager

class AuthInterceptor(private val tokenManager: ITokenManager) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        if (tokenManager.getToken() == null) {
            println("AUTH INTERCEPTOR TEST TAG - no saved token")
        }
        val requestBuilder = chain.request().newBuilder()
        tokenManager.getToken()?.let {
            println("AUTH INTERCEPTOR TEST TAG - I have intercepted with token ${tokenManager.getToken()}")
            requestBuilder.addHeader("Authorization", "Bearer $it")
        }
        return chain.proceed(requestBuilder.build())
    }
}

