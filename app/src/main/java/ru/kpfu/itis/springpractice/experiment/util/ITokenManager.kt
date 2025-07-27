package ru.kpfu.itis.springpractice.experiment.util

import androidx.core.content.edit

interface ITokenManager {

    fun saveToken(token: String)

    fun getToken(): String?
    fun clearToken()
}