package ru.kpfu.itis.springpractice.experiment.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ru.kpfu.itis.springpractice.experiment.domain.usecase.CheckAuthorizationUseCase
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class AuthCheckViewModel(
    private val checkAuthorizationUseCase: CheckAuthorizationUseCase
) : ViewModel() {

    private val _isAuthorized = MutableLiveData<Boolean>()
    val isAuthorized: LiveData<Boolean> get() = _isAuthorized

    fun checkAuthorization() {
        viewModelScope.launch {
            println(authLog("start"))

            val result = try {
                val res = checkAuthorizationUseCase.checkAuth()
                println(authLog("fetched info from use case with result $res"))
                res
            } catch (e: Exception) {
                println(authLog("failed", e = e))
                false
            }

            _isAuthorized.postValue(result)
        }
    }

    private fun authLog(message: String, e: Exception? = null): String {
        val res = "AUTH CHECK VIEW MODEL TEST TAG - $message "
        if (e != null) {
            res.plus(" e: $e")
        }
        return res
    }
}