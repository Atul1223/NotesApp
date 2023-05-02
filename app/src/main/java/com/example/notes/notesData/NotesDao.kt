package com.example.notes.notesData

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import kotlinx.coroutines.flow.Flow

@Dao
interface NotesDao {

    @Upsert
    suspend fun upsertNote(notes: Notes)

    @Delete
    suspend fun deleteNote(notes: Notes)

    @Query("SELECT * FROM  notes")
    fun getNotesData() : Flow<List<Notes>>

    @Query("SELECT * FROM notes WHERE tag = :noteTag")
    fun getNotesByTag(noteTag: String) : Flow<List<Notes>>

    @Query("SELECT * FROM notes WHERE heading = :title")
    fun getNotesByTitle(title: String) : Flow<List<Notes>>
}