package ru.kpfu.itis.springpractice.experiment.data.repositoryImpl

import ru.kpfu.itis.springpractice.experiment.data.remote.api.AdventurerAppApi
import ru.kpfu.itis.springpractice.experiment.domain.model.RegisterRequest
import ru.kpfu.itis.springpractice.experiment.domain.model.RegisterResponse
import ru.kpfu.itis.springpractice.experiment.domain.repositoryInterface.RegisterRepositoryInterface
import java.lang.Exception

class RegisterRepository(
    private val api: AdventurerAppApi
) : RegisterRepositoryInterface {

    override suspend fun register(registerForm: RegisterRequest): RegisterResponse {
        println("REGISTER REPOSITORY TEST TAG - entered register repo")
        val response = api.register(registerForm)

        if (response.isSuccessful && response.body() != null) {
            println("REGISTER REPOSITORY TEST TAG - ${response.body()}")
            return response.body()!!
        } else {
            throw Exception()
        }
    }

    override suspend fun checkIfEmailExists(email: String): Boolean {
        val response = api.checkIfEmailExists(email)

        if (response.isSuccessful && response.body() != null) {
            return response.body()!!
        } else {
            throw Exception()
        }
    }
}