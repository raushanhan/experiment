package ru.kpfu.itis.springpractice.experiment.presentation.ui.fragment

import androidx.fragment.app.viewModels
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import by.kirich1409.viewbindingdelegate.viewBinding
import com.google.android.material.snackbar.Snackbar
import ru.kpfu.itis.springpractice.experiment.AdventurerApp
import ru.kpfu.itis.springpractice.experiment.R
import ru.kpfu.itis.springpractice.experiment.databinding.FragmentProfileBinding
import ru.kpfu.itis.springpractice.experiment.presentation.extention.hide
import ru.kpfu.itis.springpractice.experiment.presentation.extention.show
import ru.kpfu.itis.springpractice.experiment.presentation.ui.MainActivity
import ru.kpfu.itis.springpractice.experiment.presentation.viewmodel.ProfileViewModel
import ru.kpfu.itis.springpractice.experiment.presentation.viewmodelfactory.ProfileViewModelFactory

class ProfileFragment : Fragment() {

    companion object {
        fun newInstance() = ProfileFragment()
    }

    private val viewBinding: FragmentProfileBinding by viewBinding(FragmentProfileBinding::bind)

    private val viewModel: ProfileViewModel by viewModels {
        val app = requireActivity().application as AdventurerApp
        ProfileViewModelFactory(
            logOutUseCase = app.logOutUseCase,
            getUserInfoUseCase = app.getUserInfoUseCase
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_profile, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(viewBinding) {
            viewModel.error.observe(viewLifecycleOwner) {
                Snackbar.make(view, R.string.profile_info_loading_error_text, Snackbar.LENGTH_SHORT)
                    .show()
            }

            viewModel.isLoading.observe(viewLifecycleOwner) { isLoading ->
                if(isLoading) {
                    profileProgressBar.show()
                } else {
                    profileProgressBar.hide()
                }
            }

            viewModel.userInfo.observe(viewLifecycleOwner) { user ->
                user?.let { it ->
                    usernameTv.text =  it.username
                    emailTv.text = it.email
                }
            }

            viewModel.getUserInfo()

            logOutBtn.setOnClickListener {
                viewModel.logOut()
                (requireActivity() as MainActivity).setToLoggedOutState()
            }
        }
    }
}