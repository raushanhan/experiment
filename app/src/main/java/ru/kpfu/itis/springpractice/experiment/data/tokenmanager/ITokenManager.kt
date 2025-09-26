package ru.kpfu.itis.springpractice.experiment.data.tokenmanager

interface ITokenManager {

    fun saveToken(token: String)

    fun getToken(): String?
    fun clearToken()
}