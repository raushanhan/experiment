package ru.kpfu.itis.springpractice.experiment.presentation.ui.recyclerview.notesrv

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ru.kpfu.itis.springpractice.experiment.domain.model.Note
import ru.kpfu.itis.springpractice.experiment.databinding.RvNoteItemBinding
import kotlin.collections.mutableListOf

class NoteRvAdapter(
    items: List<Note>,
    val onClickAction: () -> Unit
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var dataList = mutableListOf<Note>()

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

    fun submitList(items: List<Note>) {
        this.dataList = items as MutableList<Note>
        notifyDataSetChanged() // TODO: Добавить умное обновление листа
    }
}

