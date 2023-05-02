package com.example.notes.viewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.notes.R
import com.example.notes.notesData.Notes
import com.example.notes.notesData.NotesDao
import com.example.notes.notesData.NotesDatabase
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject
import org.koin.java.KoinJavaComponent.inject
import java.util.Date

class NoteViewModel(
    private val dao: NotesDao): ViewModel() {


//    private val notes: Notes = Notes(
//        heading = "",
//        content = "",
//        tag = "",
//        date = Date()
//    )


    //private val dao: NotesDao by inject(NotesDao::class.java)

    private var notesList: List<Notes> = emptyList()

    private val _notesList = MutableLiveData<List<Notes>>()

    private val _currentTagType = MutableLiveData(R.drawable.vd_tag_1)

    private val _isNewNote = MutableLiveData(true)

    fun getCurrentTagType(): LiveData<Int> = _currentTagType

    fun getIsNewNote(): LiveData<Boolean> = _isNewNote

    fun getNotesList() : LiveData<List<Notes>> = _notesList


    fun setCurrentTagType(resID: Int) {
        _currentTagType.value = resID
    }

    fun setIsNewNote(isNewNote: Boolean) {
        _isNewNote.value = isNewNote
    }

    fun saveNotesData(notes: Notes) {
        viewModelScope.launch {
            dao.upsertNote(notes)
        }
    }

    fun getNotesData() {

    }

    fun getNotesByTag(tag: String){

    }

    fun getNotesByTitle(title: String){
        viewModelScope.launch {
            dao.getNotesByTitle(title).collect {
                _notesList.value =  it
            }
         }
    }

    fun deleteNote(notes: Notes){
        viewModelScope.launch {
            dao.deleteNote(notes)
        }
    }
}