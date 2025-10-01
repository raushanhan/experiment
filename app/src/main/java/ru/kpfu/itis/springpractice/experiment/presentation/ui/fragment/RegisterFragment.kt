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
import ru.kpfu.itis.springpractice.experiment.presentation.viewmodel.LoginViewModel
import ru.kpfu.itis.springpractice.experiment.presentation.viewmodel.RegisterViewModel
import ru.kpfu.itis.springpractice.experiment.presentation.viewmodelfactory.LoginViewModelFactory
import ru.kpfu.itis.springpractice.experiment.presentation.viewmodelfactory.RegisterViewModelFactory

class RegisterFragment : Fragment() {

    private val viewBinding: FragmentRegisterBinding by viewBinding(FragmentRegisterBinding::bind)

    private val registerViewModel: RegisterViewModel by viewModels {
        val app = requireActivity().application as AdventurerApp
        RegisterViewModelFactory(
            registerUseCase = app.registerUseCase
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
                Snackbar.make(view, R.string.login_error_text, Snackbar.LENGTH_SHORT)
                    .show()
            }

            registerViewModel.registerSuccess.observe(viewLifecycleOwner) { isSuccessful ->
                if (isSuccessful) {
                    // авторизация после регистрации происходит в вью модели
                    (requireActivity() as MainActivity).setToMainNavGraph()
                } else {
                    Snackbar.make(view, R.string.unsuccessful_registration, Snackbar.LENGTH_SHORT)
                        .show()
                }
            }

            registerViewModel.passwordValidation.observe(viewLifecycleOwner) { isValid ->
                if (!isValid) {
                    passwordLayout.error = "Введите пароль"
                } else {
                    passwordLayout.error = null
                }
            }

            registerViewModel.usernameValidation.observe(viewLifecycleOwner) { isValid ->
                if (!isValid) {
                    usernameLayout.error = "Введите email"
                } else {
                    usernameLayout.error = null
                }
            }

            submitRegistrationBtn.setOnClickListener {
                registerViewModel.submitAuthorization(
                    .text.toString(),
                    password.text.toString()
                )
            }

            registerBtn.setOnClickListener {
                findNavController().navigate(R.id.register_fragment)
            }
        }
    }
}