package com.example.notes.notesData

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date

@Entity(tableName = "notes")
data class Notes(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val heading: String,
    val content: String,
    val tag: String,
    //val date: Date
)
