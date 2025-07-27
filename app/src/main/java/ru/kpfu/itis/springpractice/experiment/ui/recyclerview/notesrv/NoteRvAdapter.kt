package ru.kpfu.itis.springpractice.experiment.ui.recyclerview.notesrv

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ru.kpfu.itis.springpractice.experiment.model.Note
import ru.kpfu.itis.springpractice.experiment.databinding.RvNoteItemBinding
import kotlin.collections.mutableListOf

class NoteRvAdapter(
    items: List<Note>,
    val onClickAction: () -> Unit
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val dataList = mutableListOf<Note>()

    init {
        dataList.addAll(items)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RecyclerView.ViewHolder {
        return NoteRvViewHolder(
            onClickAction,
            viewBinding = RvNoteItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(
        holder: RecyclerView.ViewHolder,
        position: Int
    ) {
        (holder as? NoteRvViewHolder)?.bindItem(dataList[position])
    }

    override fun getItemCount(): Int {
        return dataList.size
    }
}

