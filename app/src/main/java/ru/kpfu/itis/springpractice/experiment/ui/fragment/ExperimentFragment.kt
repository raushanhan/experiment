package ru.kpfu.itis.springpractice.experiment.ui.fragment

import android.app.Application
import androidx.fragment.app.viewModels
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import by.kirich1409.viewbindingdelegate.viewBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import ru.kpfu.itis.springpractice.experiment.AdventurerApp
import ru.kpfu.itis.springpractice.experiment.R
import ru.kpfu.itis.springpractice.experiment.databinding.FragmentExperimentBinding
import ru.kpfu.itis.springpractice.experiment.model.Note
import ru.kpfu.itis.springpractice.experiment.repository.AuthRepository
import ru.kpfu.itis.springpractice.experiment.repository.NotesRepository
import ru.kpfu.itis.springpractice.experiment.ui.recyclerview.notesrv.NoteRvAdapter
import ru.kpfu.itis.springpractice.experiment.ui.viewModel.ExperimentViewModel

class ExperimentFragment : Fragment() {

    private val viewBinding: FragmentExperimentBinding by viewBinding(FragmentExperimentBinding::bind)
    private val viewModel: ExperimentViewModel by viewModels()
    private lateinit var rvAdapter: NoteRvAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_experiment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val app = requireActivity().application as AdventurerApp
        viewLifecycleOwner.lifecycleScope.launch {
            try {
                app.authRepository.login("email@email.com", "rrr")
                println("FRAGMENT TEST TAG - logged in")
                val items = app.notesRepository.getNotes()
                println("FRAGMENT TEST TAG - $items")
                rvAdapter = NoteRvAdapter(
                    items = items,
                    onClickAction = {
                        // действие при клике
                    }
                )
                viewBinding.notesRv.apply {
                    layoutManager = LinearLayoutManager(
                        requireContext(),
                        RecyclerView.VERTICAL,
                        false
                    )
                    adapter = rvAdapter
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}

