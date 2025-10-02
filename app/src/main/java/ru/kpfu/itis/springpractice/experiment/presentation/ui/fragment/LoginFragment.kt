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
import ru.kpfu.itis.springpractice.experiment.databinding.FragmentLoginBinding
import ru.kpfu.itis.springpractice.experiment.presentation.extention.hide
import ru.kpfu.itis.springpractice.experiment.presentation.extention.show
import ru.kpfu.itis.springpractice.experiment.presentation.ui.MainActivity
import ru.kpfu.itis.springpractice.experiment.presentation.viewmodel.LoginViewModel
import ru.kpfu.itis.springpractice.experiment.presentation.viewmodelfactory.LoginViewModelFactory

class LoginFragment : Fragment() {

    private val viewBinding: FragmentLoginBinding by viewBinding(FragmentLoginBinding::bind)

    companion object {
        fun newInstance() = LoginFragment()
    }

    private val viewModel: LoginViewModel by viewModels {
        val app = requireActivity().application as AdventurerApp
        LoginViewModelFactory(
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
        return inflater.inflate(R.layout.fragment_login, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with (viewBinding) {

            viewModel.isLoading.observe(viewLifecycleOwner) { isLoading ->
                if (isLoading) {
                    loginProgressBar.show()
                } else {
                    loginProgressBar.hide()
                }
            }

            viewModel.error.observe(viewLifecycleOwner) {
                Snackbar.make(view, R.string.login_error_text, Snackbar.LENGTH_SHORT)
                    .show()
            }

            viewModel.authSuccess.observe(viewLifecycleOwner) { isSuccessful ->
                if (isSuccessful) {
                    (requireActivity() as MainActivity).setToLoggedInState()
                } else {
                    Snackbar.make(view, R.string.wrong_user_credentials_text, Snackbar.LENGTH_SHORT)
                        .show()
                }
            }

            viewModel.passwordValidation.observe(viewLifecycleOwner) { isValid ->
                if (!isValid) {
                    passwordLayout.error = getString(R.string.empty_password_error_text)
                } else {
                    passwordLayout.error = null
                }
            }

            viewModel.usernameValidation.observe(viewLifecycleOwner) { isValid ->
                if (!isValid) {
                    usernameLayout.error = getString(R.string.empty_email_error_text)
                } else {
                    usernameLayout.error = null
                }
            }

            submitAuthorizationBtn.setOnClickListener {
                viewModel.submitAuthorization(
                    username.text.toString(),
                    password.text.toString()
                )
            }

            registerBtn.setOnClickListener {
                findNavController().navigate(R.id.register_fragment)
            }
        }
    }


}