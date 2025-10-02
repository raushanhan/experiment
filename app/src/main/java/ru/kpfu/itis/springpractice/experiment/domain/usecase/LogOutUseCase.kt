package ru.kpfu.itis.springpractice.experiment.domain.usecase

import ru.kpfu.itis.springpractice.experiment.data.tokenmanager.ITokenManager
import ru.kpfu.itis.springpractice.experiment.domain.model.Authorization

class LogOutUseCase(
    private val authorization: Authorization,
    private val tokenManager: ITokenManager
) {

    fun logOut() {
        tokenManager.clearToken()
        authorization.loggedInUser = null
        authorization.isAuthorized = false
    }
}