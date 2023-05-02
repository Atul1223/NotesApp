package com.example.notes.notesData

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.notes.viewModels.NoteViewModel


class NotesViewModelFactory(private val dao: NotesDao) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return NoteViewModel(dao) as T
    }
}