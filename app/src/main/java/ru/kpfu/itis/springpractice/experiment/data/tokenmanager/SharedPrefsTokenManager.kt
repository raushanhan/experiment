package ru.kpfu.itis.springpractice.experiment.data.tokenmanager

import android.content.Context
import androidx.core.content.edit

class SharedPrefsTokenManager(context: Context): ITokenManager {
    private val prefs = context.getSharedPreferences("jwt_prefs", Context.MODE_PRIVATE)

    override fun saveToken(token: String) {
        println("TOKEN MANAGER TEST TAG - saved token to shared prefs")
        prefs.edit { putString("jwt_token", token) }
    }

    override fun getToken(): String? = prefs.getString("jwt_token", null)
    override fun clearToken() = prefs.edit { remove("jwt_token") }
}