package ru.kpfu.itis.springpractice.experiment.data.repositoryImpl

import ru.kpfu.itis.springpractice.experiment.data.remote.api.AdventurerAppApi
import ru.kpfu.itis.springpractice.experiment.domain.model.LoginRequest
import ru.kpfu.itis.springpractice.experiment.domain.repositoryInterface.AuthRepositoryInterface
import ru.kpfu.itis.springpractice.experiment.domain.tokenmanager.ITokenManager

class AuthRepository(
    private val api: AdventurerAppApi,
    private val tokenManager: ITokenManager
) : AuthRepositoryInterface {
    override suspend fun login(email: String, password: String): Boolean {

        println("LOG - entered authRepository.login(email=$email,password=$password)")
        val response = api.login(LoginRequest(email, password))
        println("LOG - after log in - response: ${response.isSuccessful}, ${response.message()}")
        return if (response.isSuccessful && response.body() != null) {
            tokenManager.saveToken(response.body()!!.token)
            println("LOG - saved token ${tokenManager.getToken()}")
            true
        } else {
            false
        }
    }
}

