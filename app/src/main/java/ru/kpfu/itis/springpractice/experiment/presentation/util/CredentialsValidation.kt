package ru.kpfu.itis.springpractice.experiment.presentation.util

object CredentialsValidation {

    fun validatePassword(password: String): CredentialValidity {
        return when {
            password.isEmpty() -> {
                CredentialValidity.EMPTY_CRED
            }
            password.length < 6 -> {
                CredentialValidity.INVALID_CRED
            }
            else -> {
                CredentialValidity.VALID_CRED
            }
        }
    }

    fun validateUsername(username: String): CredentialValidity {
        return when {
            username.isEmpty() -> {
                CredentialValidity.EMPTY_CRED
            }
            else -> {
                CredentialValidity.VALID_CRED
            }
        }
    }

    fun validateEmail(email: String): CredentialValidity {
        val emailRegex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$".toRegex()

        return when {
            email.isEmpty() -> CredentialValidity.EMPTY_CRED
            !email.matches(emailRegex) -> CredentialValidity.INVALID_CRED
            else -> CredentialValidity.VALID_CRED
        }
    }
}

enum class CredentialValidity {
    EMPTY_CRED, INVALID_CRED, VALID_CRED
}