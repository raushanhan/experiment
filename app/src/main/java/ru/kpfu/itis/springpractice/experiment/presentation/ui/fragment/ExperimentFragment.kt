package ru.kpfu.itis.springpractice.experiment.presentation.ui.fragment

import androidx.fragment.app.viewModels
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import by.kirich1409.viewbindingdelegate.viewBinding
import com.google.android.material.snackbar.Snackbar
import ru.kpfu.itis.springpractice.experiment.AdventurerApp
import ru.kpfu.itis.springpractice.experiment.R
import ru.kpfu.itis.springpractice.experiment.databinding.FragmentExperimentBinding
import ru.kpfu.itis.springpractice.experiment.extention.*
import ru.kpfu.itis.springpractice.experiment.presentation.ui.recyclerview.notesrv.NoteRvAdapter
import ru.kpfu.itis.springpractice.experiment.presentation.viewmodel.ExperimentViewModel
import ru.kpfu.itis.springpractice.experiment.presentation.viewmodelfactory.ExperimentViewModelFactory

class ExperimentFragment : Fragment() {

    private val viewBinding: FragmentExperimentBinding by viewBinding(FragmentExperimentBinding::bind)
    private val viewModel: ExperimentViewModel by viewModels {
        // временно!!!!!!
        ExperimentViewModelFactory(
            (requireActivity().application as AdventurerApp).authorizeUseCase,
            (requireActivity().application as AdventurerApp).loadAuthorizedUserNotesUseCase
        )
    }
    private lateinit var rvAdapter: NoteRvAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_experiment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.loadData()
        rvAdapter = NoteRvAdapter(
            items = emptyList(),
            onClickAction = {
                // действие при клике
            }
        )
        viewBinding.notesRv.apply {
            layoutManager = LinearLayoutManager(
                requireContext(),
                RecyclerView.VERTICAL,
                false)
            adapter = rvAdapter
        }
        viewModel.notes.observe(viewLifecycleOwner) { items ->
            rvAdapter.submitList(items)
        }
        viewModel.isLoading.observe(viewLifecycleOwner) { isLoading ->
            with(viewBinding) {
                if (isLoading) {
                    notesProgressBar.show()
                } else {
                    notesProgressBar.hide()
                }
            }
        }
        viewModel.error.observe(viewLifecycleOwner) {
            Snackbar.make(view, R.string.notes_list_loading_error_text, Snackbar.LENGTH_SHORT)
                .show()
        }
    }
}

