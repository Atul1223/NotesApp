package com.example.notes.viewModels

import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.notes.notesData.NotesDatabase
import org.koin.java.KoinJavaComponent.inject

open class BaseViewModel: ViewModel() {

   //protected val notesDatabase: NotesDatabase by inject(NotesDatabase::class.java)

}