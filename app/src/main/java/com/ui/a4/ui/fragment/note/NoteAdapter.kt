package com.ui.a4.ui.fragment.note

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ui.a4.model.NoteModel
import com.ui.a4.ui.utils.App
import com.ui.databinding.ItemNoteBinding

class NoteAdapter(private val listener: NoteListener) :
    RecyclerView.Adapter<NoteAdapter.NoteViewHolder>() {
    private var list = ArrayList<NoteModel>()

    @SuppressLint("NotifyDataSetChanged")
    fun setList(list: ArrayList<NoteModel>) {
        this.list = list
        notifyDataSetChanged()
    }

    fun deleteNote(position: Int) {
        App.db.getDao().deleteNote(list.removeAt(position))
        notifyItemRemoved(position)
    }

    fun getList(): List<NoteModel> {
        return list
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int)=
        NoteViewHolder(
            ItemNoteBinding.inflate(
                LayoutInflater.from(parent.context),
                parent, false
            )
        )

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        holder.onBind(list[position])
        holder.itemView.setOnClickListener {
            listener.edit(holder.adapterPosition)
        }
    }

    override fun getItemCount(): Int = list.size

    class NoteViewHolder(private val binding: ItemNoteBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun onBind(model: NoteModel) {
            binding.tvTitle.text = model.title
            binding.tvDescription.text = model.description
        }

    }

    interface NoteListener {
        fun edit(position: Int)
    }
}