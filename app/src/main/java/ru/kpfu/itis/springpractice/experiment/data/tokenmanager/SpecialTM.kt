package ru.kpfu.itis.springpractice.experiment.data.tokenmanager

class SpecialTM : ITokenManager {
    private var token: String? = null

    override fun saveToken(token: String) {
        this.token = token
    }

    override fun getToken(): String? {
        return token
    }

    override fun clearToken() {
        token = null
    }

}