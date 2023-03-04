package com.example.notes.ViewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class HomeViewModel: ViewModel() {

    private var notesListSize = 0

    private val _notesListSize = MutableLiveData(notesListSize)

    fun getNotesListSize() : LiveData<Int> = _notesListSize

    fun addToNotesList() {
        notesListSize++
        _notesListSize.value = notesListSize
    }

    fun removeFromNotesList() {
        notesListSize--
        _notesListSize.value = notesListSize
    }
}