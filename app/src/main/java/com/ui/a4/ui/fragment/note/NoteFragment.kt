package com.ui.a4.ui.fragment.note

import android.os.Bundle
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.ui.R
import com.ui.a4.base.BaseFragment
import com.ui.a4.ui.utils.App
import com.ui.databinding.FragmentNoteBinding
import java.util.*

class NoteFragment :
    BaseFragment<FragmentNoteBinding>(FragmentNoteBinding::inflate),
    NoteAdapter.NoteListener{
    private lateinit var adapter: NoteAdapter

    override fun setupUI() {
        adapter = NoteAdapter(this)
        binding.rvNote.adapter = adapter
    }

    override fun setupObserver() {
        super.setupObserver()
        adapter.setList(App.db.getDao().getAllNote() as ArrayList)

        binding.btnAddNote.setOnClickListener {
            controller.navigate(R.id.addNoteFragment)
        }
        deleteNote()
    }

    private fun deleteNote() {
        val simpleCallback = object :
            ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.RIGHT or ItemTouchHelper.LEFT) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                adapter.deleteNote(viewHolder.adapterPosition)
                adapter.notifyItemChanged(viewHolder.adapterPosition)
            }
        }
        val itemTouchHelper = ItemTouchHelper(simpleCallback)
        itemTouchHelper.attachToRecyclerView(binding.rvNote)
    }

    override fun edit(position: Int) {
        val bundle = Bundle()
        val noteModel = adapter.getList().get(position)
        noteModel.id?.let { bundle.putInt("id", it) }
        bundle.putSerializable("edit", noteModel)
        controller.navigate(R.id.addNoteFragment, bundle)
    }
}
