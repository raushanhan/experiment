package ru.kpfu.itis.springpractice.experiment.presentation.ui.recyclerview.notesrv

import android.view.LayoutInflater
import android.view.MenuInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import ru.kpfu.itis.springpractice.experiment.domain.model.Note
import ru.kpfu.itis.springpractice.experiment.databinding.RvNoteItemBinding
import kotlin.collections.mutableListOf

class NoteRvAdapter(
    items: List<Note>,
    private val onClickAction: (noteId: Long) -> Unit,
    private val onMenuItemClick: (note: Note, menuItemId: Int) -> Unit,
    private val menuInflater: MenuInflater,
    private val imageLoadingFun: (path: String, imageView: ImageView) -> Unit
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
            onClickAction = onClickAction,
            viewBinding = RvNoteItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            ),
            menuInflater = menuInflater,
            onMenuItemClick = onMenuItemClick,
            imageLoadingFun = imageLoadingFun
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

    fun submitList(newList: List<Note>) {
        val oldList = ArrayList(dataList)
        dataList.clear()
        dataList.addAll(newList)

        val diffResult = DiffUtil.calculateDiff(object : DiffUtil.Callback() {
            override fun getOldListSize(): Int = oldList.size
            override fun getNewListSize(): Int = newList.size
            override fun areItemsTheSame(oldPos: Int, newPos: Int): Boolean {
                return oldList[oldPos].id == newList[newPos].id
            }
            override fun areContentsTheSame(oldPos: Int, newPos: Int): Boolean {
                return oldList[oldPos] == newList[newPos]
            }
        })

        diffResult.dispatchUpdatesTo(this)
    }
}

