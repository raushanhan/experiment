package ru.kpfu.itis.springpractice.experiment.presentation.ui.recyclerview.notesrv

import androidx.recyclerview.widget.RecyclerView
import ru.kpfu.itis.springpractice.experiment.domain.model.Note
import ru.kpfu.itis.springpractice.experiment.databinding.RvNoteItemBinding

class NoteRvViewHolder(
    private val onClickAction: () -> Unit,
    val viewBinding: RvNoteItemBinding
) : RecyclerView.ViewHolder(viewBinding.root) {

    init {
        viewBinding.root.setOnClickListener { onClickAction() }
    }

    fun bindItem(model: Note) {
        with(viewBinding) {
            noteNameTv.text = model.title
            noteDetailsTv.text = model.content
        }
    }
}

