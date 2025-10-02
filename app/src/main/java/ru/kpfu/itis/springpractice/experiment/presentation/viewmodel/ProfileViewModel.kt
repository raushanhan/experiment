package ru.kpfu.itis.springpractice.experiment.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import ru.kpfu.itis.springpractice.experiment.domain.model.User
import ru.kpfu.itis.springpractice.experiment.domain.usecase.GetUserInfoUseCase
import ru.kpfu.itis.springpractice.experiment.domain.usecase.LogOutUseCase
import java.lang.Exception

class ProfileViewModel(
    private val getUserInfoUseCase: GetUserInfoUseCase,
    private val logOutUseCase: LogOutUseCase
) : ViewModel() {

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    private val _error = MutableLiveData<String?>()
    val error: LiveData<String?> = _error

    private val _userInfo = MutableLiveData<User?>()
    val userInfo: LiveData<User?> = _userInfo

    fun getUserInfo() {
        viewModelScope.launch {
            try {
                _isLoading.value = true
                _userInfo.value = getUserInfoUseCase.getUserInfo()
            } catch(e: Exception) {
                _error.value = e.message
            } finally {
                _isLoading.value = false
            }
        }
    }

    fun logOut() {
        logOutUseCase.logOut()
    }
}