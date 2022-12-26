package com.ui.a4.ui.fragment.addNote

import com.ui.R
import com.ui.a4.base.BaseFragment
import com.ui.a4.model.NoteModel
import com.ui.a4.ui.utils.App
import com.ui.databinding.FragmentAddNoteBinding


class AddNoteFragment : BaseFragment<FragmentAddNoteBinding>(FragmentAddNoteBinding::inflate) {
    override fun setupUI() {
        saveNote()
    }

    private fun saveNote() {
        binding.btnSave.setOnClickListener {
            val title = binding.etTitle.text.toString()
            val des = binding.etDescription.text.toString()

            App.db.getDao().addNote(NoteModel(title = title, description = des))
            controller.navigate(R.id.noteFragment)
        }
    }
}