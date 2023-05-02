package com.example.notes.notesData

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [Notes::class],
    version = 1
)
abstract class NotesDatabase: RoomDatabase(){

    abstract fun dao(): NotesDao

}