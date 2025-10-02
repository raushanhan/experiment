package ru.kpfu.itis.springpractice.experiment.presentation.ui.fragment

import androidx.fragment.app.viewModels
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.google.android.material.snackbar.Snackbar
import ru.kpfu.itis.springpractice.experiment.AdventurerApp
import ru.kpfu.itis.springpractice.experiment.R
import ru.kpfu.itis.springpractice.experiment.databinding.FragmentRegisterBinding
import ru.kpfu.itis.springpractice.experiment.presentation.extention.hide
import ru.kpfu.itis.springpractice.experiment.presentation.extention.show
import ru.kpfu.itis.springpractice.experiment.presentation.ui.MainActivity
import ru.kpfu.itis.springpractice.experiment.presentation.util.CredentialValidity
import ru.kpfu.itis.springpractice.experiment.presentation.viewmodel.RegisterViewModel
import ru.kpfu.itis.springpractice.experiment.presentation.viewmodelfactory.RegisterViewModelFactory

class RegisterFragment : Fragment() {

    private val viewBinding: FragmentRegisterBinding by viewBinding(FragmentRegisterBinding::bind)

    private val registerViewModel: RegisterViewModel by viewModels {
        val app = requireActivity().application as AdventurerApp
        RegisterViewModelFactory(
            registerUseCase = app.registerUseCase,
            authorizeUseCase = app.authorizeUseCase
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_register, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(viewBinding) {
            registerViewModel.isLoading.observe(viewLifecycleOwner) { isLoading ->
                if (isLoading) {
                    registerProgressBar.show()
                } else {
                    registerProgressBar.hide()
                }
            }

            registerViewModel.error.observe(viewLifecycleOwner) {
                Snackbar.make(view, R.string.registration_error_text, Snackbar.LENGTH_SHORT)
                    .show()
            }

            registerViewModel.registerSuccess.observe(viewLifecycleOwner) { isSuccessful ->
                if (isSuccessful) {
                    println("REGISTER FRAGMENT TEST TAG - successful register, now log in")

                    val email = email.text.toString()
                    val password = password.text.toString()
                    registerViewModel.authAfterRegister(email, password)


                } else {
                    println("REGISTER FRAGMENT TEST TAG - register unsuccess")
                    Snackbar.make(view, R.string.unsuccessful_registration, Snackbar.LENGTH_SHORT)
                        .show()
                }
            }

            registerViewModel.loginSuccess.observe(viewLifecycleOwner) { isSuccessful ->
                if (isSuccessful) {
                    println("REGISTER FRAGMENT TEST TAG - successful login, now set to logged in state")
                    (requireActivity() as MainActivity).setToLoggedInState()
                } else {
                    println("REGISTER FRAGMENT TEST TAG - login unsuccess")
                    Snackbar.make(view, R.string.login_failed, Snackbar.LENGTH_SHORT)
                        .show()
                }
            }

            registerViewModel.passwordValidation.observe(viewLifecycleOwner) { validity ->
                when (validity) {
                    CredentialValidity.EMPTY_CRED -> passwordLayout.error =
                        getString(R.string.empty_password_error_text)

                    CredentialValidity.INVALID_CRED -> passwordLayout.error =
                        getString(R.string.invalid_password_error_text)

                    CredentialValidity.VALID_CRED -> passwordLayout.error = null
                }
            }

            registerViewModel.usernameValidation.observe(viewLifecycleOwner) { validity ->
                when (validity) {
                    CredentialValidity.EMPTY_CRED -> usernameLayout.error =
                        getString(R.string.empty_username_error_text)

                    CredentialValidity.INVALID_CRED -> usernameLayout.error =
                        getString(R.string.invalid_username_error_text)

                    CredentialValidity.VALID_CRED -> usernameLayout.error = null
                }
            }

            registerViewModel.emailValidation.observe(viewLifecycleOwner) { validity ->
                when (validity) {
                    CredentialValidity.EMPTY_CRED -> emailLayout.error =
                        getString(R.string.empty_email_error_text)

                    CredentialValidity.INVALID_CRED -> emailLayout.error =
                        getString(R.string.invalid_email_error_text)

                    CredentialValidity.VALID_CRED -> emailLayout.error = null
                }
            }


            submitRegistrationBtn.setOnClickListener {
                registerViewModel.submitRegistration(
                    email = email.text.toString(),
                    username = username.text.toString(),
                    password = password.text.toString()
                )
            }

            getBackToLogInBtn.setOnClickListener {
                findNavController().navigate(R.id.login_fragment)
            }
        }
    }
}