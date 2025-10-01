package ru.kpfu.itis.springpractice.experiment.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import ru.kpfu.itis.springpractice.experiment.domain.usecase.AuthorizeUseCase

class LoginViewModel(
    private val authorizeUseCase: AuthorizeUseCase
) : ViewModel() {

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    private val _error = MutableLiveData<String?>()
    val error: LiveData<String?> = _error

    private val _usernameValidation = MutableLiveData<Boolean>()
    val usernameValidation: LiveData<Boolean> = _usernameValidation

    private val _passwordValidation = MutableLiveData<Boolean>()
    val passwordValidation: LiveData<Boolean> = _passwordValidation

    private val _authSuccess = MutableLiveData<Boolean>()
    val authSuccess: LiveData<Boolean> = _authSuccess


    fun submitAuthorization(username: String, password: String) {
        viewModelScope.launch {
            try {
                println(authLog(state = "entered", withUsername= username))
                _isLoading.value = true
                if (
                    isLoginFormFieldValidCheck(username, _usernameValidation) &&
                    isLoginFormFieldValidCheck(password, _passwordValidation)
                ) {
                    _authSuccess.value = authorizeUseCase.authorize(username, password)
                }
            } catch(e: Exception) {
                _error.value = e.message
                println(authLog(state = "failed", withUsername = username, e = e))
            } finally {
                _isLoading.value = false
                println(authLog(state = "finished", withUsername = username))
            }
        }
    }

    private fun isLoginFormFieldValidCheck(value: String, validation: MutableLiveData<Boolean>): Boolean {
        val res = value.isNotEmpty()
        validation.value = res
        return res
    }

    private fun authLog(state: String, withUsername: String, e: Exception? = null): String {
        val res = "LOGIN VIEW MODEL TEST TAG - $state authentification with username: $withUsername"
        if (e != null) {
            res.plus(" e: $e")
        }
        return res
    }
}