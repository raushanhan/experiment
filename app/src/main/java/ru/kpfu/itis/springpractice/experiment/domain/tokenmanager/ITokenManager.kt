package ru.kpfu.itis.springpractice.experiment.domain.tokenmanager

interface ITokenManager {

    fun saveToken(token: String)

    fun getToken(): String?
    fun clearToken()
}