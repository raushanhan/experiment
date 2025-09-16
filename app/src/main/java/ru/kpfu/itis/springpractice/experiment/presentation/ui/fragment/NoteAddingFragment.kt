package ru.kpfu.itis.springpractice.experiment.presentation.ui.fragment

import androidx.fragment.app.viewModels
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import ru.kpfu.itis.springpractice.experiment.AdventurerApp
import ru.kpfu.itis.springpractice.experiment.R
import ru.kpfu.itis.springpractice.experiment.databinding.FragmentNoteAddingBinding
import ru.kpfu.itis.springpractice.experiment.domain.model.Note
import ru.kpfu.itis.springpractice.experiment.domain.model.NoteAddRequest
import ru.kpfu.itis.springpractice.experiment.presentation.viewmodel.NoteAddingViewModel
import ru.kpfu.itis.springpractice.experiment.presentation.viewmodelfactory.NoteAddingViewModelFactory
import ru.kpfu.itis.springpractice.experiment.presentation.ui.locationManager.LocationManager
import java.io.File

class NoteAddingFragment : Fragment() {

    private val viewBinding: FragmentNoteAddingBinding by viewBinding(FragmentNoteAddingBinding::bind)

    companion object {
        fun newInstance() = NoteAddingFragment()
    }

    private val viewModel: NoteAddingViewModel by viewModels {
        val app = requireActivity().application as AdventurerApp
        NoteAddingViewModelFactory(
            app.addNoteUseCase,
            app.uploadImageUseCase
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val locationManager = LocationManager(activity = requireActivity())

        with(viewBinding) {
            proceedAddingANoteButton.setOnClickListener { view ->
                val title = noteTitleEt.text.toString()
                val content = noteDescriptionEt.text.toString()
                // todo: fix
                val imageUrl = "/storage/emulated/0/Download/test.jpg" // todo: fix
                // todo: fix
                if (title.isEmpty()) {
                    TODO("Implement making title bar red")
                } else if (content.isEmpty()) {
                    TODO("Implement making content bar red")
                } else {
                    val note = Note(
                        id = 0,
                        imageUrl = imageUrl,
                        title = title,
                        content = content,
                    )
                    println("NOTE ADDING FRAGMENT TEST TAG - note created: $note")
                    viewModel.addNote(note, locationManager)
                    view.findNavController().navigate(R.id.notes_fragment)
                }
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_note_adding, container, false)
    }
}