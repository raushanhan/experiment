package ru.kpfu.itis.springpractice.experiment.presentation.ui.recyclerview.notesrv

import android.view.ContextMenu
import android.view.MenuInflater
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import ru.kpfu.itis.springpractice.experiment.R
import ru.kpfu.itis.springpractice.experiment.domain.model.Note
import ru.kpfu.itis.springpractice.experiment.databinding.RvNoteItemBinding

class NoteRvViewHolder(
    private val onClickAction: (note: Note) -> Unit,
    val viewBinding: RvNoteItemBinding,
    val menuInflater: MenuInflater,
    private val onMenuItemClick: (note: Note, menuItemId: Int) -> Unit,
) : RecyclerView.ViewHolder(viewBinding.root), View.OnCreateContextMenuListener {

    private lateinit var currentNote: Note // возможно стоит сделать Note? вместо lateinit
                                            // также можно здесь хранить просто айдишник

    init {
        viewBinding.root.apply {
            setOnClickListener { onClickAction(currentNote) }
            setOnCreateContextMenuListener(this@NoteRvViewHolder as View.OnCreateContextMenuListener?)
        }
    }

    fun bindItem(note: Note) {
        with(viewBinding) {
            noteNameTv.text = note.title
            noteDetailsTv.text = note.content
        }
        currentNote = note
    }

    override fun onCreateContextMenu(
        menu: ContextMenu?,
        view: View?,
        menuInfo: ContextMenu.ContextMenuInfo?
    ) {
        menuInflater.inflate(R.menu.notes_list_context_menu, menu)
        menu?.findItem(R.id.notes_list_context_menu_item_delete)?.setOnMenuItemClickListener {
            onMenuItemClick(currentNote, R.id.notes_list_context_menu_item_delete)
            true
        }
    }
}

