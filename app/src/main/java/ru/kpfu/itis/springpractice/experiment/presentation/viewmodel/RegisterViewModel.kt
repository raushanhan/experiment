package ru.kpfu.itis.springpractice.experiment.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import ru.kpfu.itis.springpractice.experiment.domain.model.RegisterRequest
import ru.kpfu.itis.springpractice.experiment.domain.usecase.AuthorizeUseCase
import ru.kpfu.itis.springpractice.experiment.domain.usecase.RegisterUseCase
import ru.kpfu.itis.springpractice.experiment.presentation.util.CredentialValidity
import ru.kpfu.itis.springpractice.experiment.presentation.util.CredentialsValidation

class RegisterViewModel(
    private val registerUseCase: RegisterUseCase,
    private val authorizeUseCase: AuthorizeUseCase
) : ViewModel() {

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    private val _error = MutableLiveData<String?>()
    val error: LiveData<String?> = _error

    private val _usernameValidation = MutableLiveData<CredentialValidity>()
    val usernameValidation: LiveData<CredentialValidity> = _usernameValidation

    private val _passwordValidation = MutableLiveData<CredentialValidity>()
    val passwordValidation: LiveData<CredentialValidity> = _passwordValidation

    private val _emailValidation = MutableLiveData<CredentialValidity>()
    val emailValidation: LiveData<CredentialValidity> = _emailValidation

    private val _registerSuccess = MutableLiveData<Boolean>()
    val registerSuccess: LiveData<Boolean> = _registerSuccess

    private val _loginSuccess = MutableLiveData<Boolean>()
    val loginSuccess: LiveData<Boolean> = _loginSuccess

    private val _emailIsUsedFlag = MutableLiveData<Boolean>()
    val emailIsUsedFlag: LiveData<Boolean> = _emailIsUsedFlag


    fun submitRegistration(email: String, username: String, password: String) {
        viewModelScope.launch {
            try {
                println(authLog(state = "entered", withUsername = username))
                _isLoading.value = true

                val emailResult = CredentialsValidation.validateEmail(email)
                val usernameResult = CredentialsValidation.validateUsername(username)
                val passwordResult = CredentialsValidation.validatePassword(password)

                _emailValidation.value = emailResult
                _usernameValidation.value = usernameResult
                _passwordValidation.value = passwordResult

                val allValid = listOf(emailResult, usernameResult, passwordResult)
                    .all { it == CredentialValidity.VALID_CRED }

                var emailUsed: Boolean
                if (email.isNotEmpty()) {
                    emailUsed = registerUseCase.checkIfEmailExists(email)
                    _emailIsUsedFlag.value = emailUsed
                } else {
                    emailUsed = false
                }
                if (allValid && !emailUsed) {
                    println("TEST TAG $allValid, ${!emailUsed}")
                    val success = registerUseCase.register(
                        RegisterRequest(
                            email = email,
                            username = username,
                            password = password
                        )
                    )
                    _registerSuccess.value = true
                } else {
                    _registerSuccess.value = false
                }

            } catch (e: Exception) {
                _error.value = e.message
                println(authLog(state = "failed", withUsername = username, e = e))
            } finally {
                _isLoading.value = false
                println(authLog(state = "finished", withUsername = username))
            }
        }
    }

    fun authAfterRegister(email: String, password: String) {
        viewModelScope.launch {
            try {
                _loginSuccess.value = authorizeUseCase.authorize(email, password)
            } catch (e: Exception) {
                _loginSuccess.value = false
                println("REGISTER VIEW MODEL TEST TAG - auth after register exception: $e")
            }
        }
    }

    private fun authLog(state: String, withUsername: String, e: Exception? = null): String {
        var res = "REGISTER VIEW MODEL TEST TAG - $state register with username: $withUsername"
        if (e != null) {
            res += " error: $e"
        }
        return res
    }
}

