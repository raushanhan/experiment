package ru.kpfu.itis.springpractice.experiment.presentation.ui.fragment

import androidx.fragment.app.viewModels
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import ru.kpfu.itis.springpractice.experiment.R
import ru.kpfu.itis.springpractice.experiment.presentation.viewmodel.NoteAddingViewModel

class NoteAddingFragment : Fragment() {

    companion object {
        fun newInstance() = NoteAddingFragment()
    }

    private val viewModel: NoteAddingViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // TODO: Use the ViewModel
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_note_adding, container, false)
    }
}