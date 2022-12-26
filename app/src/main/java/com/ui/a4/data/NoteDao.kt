package com.ui.a4.data

import androidx.room.*
import com.ui.a4.model.NoteModel

@Dao
interface NoteDao {

    @Query("SELECT * FROM notemodel")
    fun getAllNote(): List<NoteModel>

    @Insert
    fun addNote(model: NoteModel)

    @Delete
    fun deleteNote(model: NoteModel)

    @Update
    fun updateNote(model: NoteModel)
}