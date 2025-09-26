package ru.kpfu.itis.springpractice.experiment.presentation.ui.fragment

import androidx.fragment.app.viewModels
import android.os.Bundle
import android.view.LayoutInflater
import androidx.fragment.app.Fragment
import android.view.View
import android.view.ViewGroup
import by.kirich1409.viewbindingdelegate.viewBinding
import com.google.android.material.snackbar.Snackbar
import ru.kpfu.itis.springpractice.experiment.AdventurerApp
import ru.kpfu.itis.springpractice.experiment.R
import ru.kpfu.itis.springpractice.experiment.databinding.FragmentNoteDetailsBinding
import ru.kpfu.itis.springpractice.experiment.presentation.extention.hide
import ru.kpfu.itis.springpractice.experiment.presentation.extention.show
import ru.kpfu.itis.springpractice.experiment.presentation.util.MyDateTimeFormatter
import ru.kpfu.itis.springpractice.experiment.presentation.viewmodel.NoteDetailsViewModel
import ru.kpfu.itis.springpractice.experiment.presentation.viewmodelfactory.NoteDetailsViewModelFactory

class NoteDetailsFragment : Fragment() {

    private val viewBinding: FragmentNoteDetailsBinding by viewBinding(FragmentNoteDetailsBinding::bind)

    companion object {
        fun newInstance() = NoteDetailsFragment()
    }

    private val viewModel: NoteDetailsViewModel by viewModels {
        val app = requireActivity().application as AdventurerApp
        NoteDetailsViewModelFactory(
            app.pullNoteUseCase,
            app.imageLoader
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_note_details, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val noteId = arguments?.getLong("noteId") ?: -1
        println("NOTE DETAILS FRAGMENT TEST TAG - entered onViewCreated with noteId=$noteId")
        if (noteId != -1L) {

            viewModel.note.observe(viewLifecycleOwner) { note ->
                with(viewBinding) {
                    noteTitleTv.text = note.title
                    noteContentTv.text = note.content
                    noteCreationDateTv.text = getString(
                        R.string.creation_date_tv_text,
                        MyDateTimeFormatter.format(
                            requireContext(),
                            note.createdAt
                        )
                    )
                    noteLastUpdateDateTv.text = getString(
                        R.string.update_date_tv_text,
                        MyDateTimeFormatter.format(
                            requireContext(),
                            note.updatedAt
                        )
                    )

                }
                viewModel.loadImage(note.imageUrl, viewBinding.noteImageIv)
            }
            viewModel.isLoading.observe(viewLifecycleOwner) { isLoading ->
                with(viewBinding) {
                    if (isLoading) {
                        noteDetailsProgressBar.show()
                    } else {
                        noteDetailsProgressBar.hide()
                    }
                }
            }
            viewModel.error.observe(viewLifecycleOwner) {
                Snackbar.make(view, R.string.notes_list_loading_error_text, Snackbar.LENGTH_SHORT)
                    .show()
            }
            viewModel.loadNote(noteId)
        }
    }
}