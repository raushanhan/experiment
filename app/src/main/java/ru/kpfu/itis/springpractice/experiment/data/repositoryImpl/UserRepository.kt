package ru.kpfu.itis.springpractice.experiment.data.repositoryImpl

import ru.kpfu.itis.springpractice.experiment.data.remote.api.AdventurerAppApi
import ru.kpfu.itis.springpractice.experiment.domain.exception.AuthorizationException
import ru.kpfu.itis.springpractice.experiment.domain.model.User
import ru.kpfu.itis.springpractice.experiment.domain.repositoryInterface.UserRepositoryInterface
import java.lang.Exception

class UserRepository(
    private val api: AdventurerAppApi
) : UserRepositoryInterface {

    override suspend fun getUser(): User {
        val response = api.getUserInfo()

        if (response.code() == 403) {
            println("USER REPOSITORY TEST TAG - unauthorized")
            throw AuthorizationException("Not authorized")
        }

        if (response.isSuccessful && response.body() != null) {
            val user  = response.body()!!
            println("USER REPOSITORY TEST TAG - received user $user")
            return user
        } else {
            println("USER REPOSITORY TEST TAG - failed receiving user ${response.code()}")
            throw Exception()
        }
    }
}