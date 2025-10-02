package ru.kpfu.itis.springpractice.experiment.presentation.ui.fragment

import androidx.fragment.app.viewModels
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import by.kirich1409.viewbindingdelegate.viewBinding
import com.google.android.material.snackbar.Snackbar
import ru.kpfu.itis.springpractice.experiment.AdventurerApp
import ru.kpfu.itis.springpractice.experiment.R
import ru.kpfu.itis.springpractice.experiment.databinding.FragmentNotesBinding
import ru.kpfu.itis.springpractice.experiment.domain.model.Note
import ru.kpfu.itis.springpractice.experiment.presentation.extention.hide
import ru.kpfu.itis.springpractice.experiment.presentation.extention.show
import ru.kpfu.itis.springpractice.experiment.presentation.ui.recyclerview.notesrv.NoteRvAdapter
import ru.kpfu.itis.springpractice.experiment.presentation.viewmodel.NotesViewModel
import ru.kpfu.itis.springpractice.experiment.presentation.viewmodelfactory.NotesViewModelFactory

class NotesFragment : Fragment() {

    private val viewBinding: FragmentNotesBinding by viewBinding(FragmentNotesBinding::bind)
    private val viewModel: NotesViewModel by viewModels {
        val app = requireActivity().application as AdventurerApp
        NotesViewModelFactory(
            loadAuthorizedUserNotesUseCase = app.loadAuthorizedUserNotesUseCase,
            deleteNoteUseCase = app.deleteNoteUseCase,
            imageLoader = app.imageLoader
        )
    }
    private lateinit var rvAdapter: NoteRvAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_notes, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.loadData()
        rvAdapter = NoteRvAdapter(
            items = emptyList(),
            onClickAction = { noteId ->
                println("NOTE RV ADAPTER TEST TAG - started executing click action for note(id=$noteId)")
                val bundle = bundleOf("noteId" to noteId)
                val navController = requireActivity().findNavController(R.id.nav_host_fragment_container)
                navController.navigate(
                    R.id.action_notes_fragment_to_note_details_fragment,
                    bundle
                )
            },
            menuInflater = requireActivity().menuInflater,
            onMenuItemClick = { note, id ->
                onMenuItemClick(note, id)
            },
            imageLoadingFun = { path, imageView ->
                viewModel.loadImages(path, imageView)
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
        viewBinding.addNoteBtn.setOnClickListener { view ->
            view.findNavController().navigate(R.id.action_notes_fragment_to_note_adding_fragment)
        }


        registerForContextMenu(viewBinding.notesRv)


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
        viewModel.deletingError.observe(viewLifecycleOwner) {
            Snackbar.make(view, R.string.deleting_error_text, Snackbar.LENGTH_SHORT)
                .show()
        }
        viewModel.isDeleting.observe(viewLifecycleOwner) { isDeleting ->
            if (isDeleting) {
                Snackbar.make(view, R.string.deleting, Snackbar.LENGTH_SHORT).show()
            }
        }
        viewModel.isDeletedSuccessfully.observe(viewLifecycleOwner) { isDeletedSuccessfully ->
            if (isDeletedSuccessfully)
                Snackbar.make(view, R.string.deleting_success, Snackbar.LENGTH_SHORT).show()
        }
    }

    private fun onMenuItemClick(note: Note, menuItemId: Int) {
        when (menuItemId) {
            R.id.notes_list_context_menu_item_delete ->
                deleteNote(note.id)
        }
    }

    private fun deleteNote(noteId: Long) {
        viewModel.deleteNote(noteId)
    }
}

