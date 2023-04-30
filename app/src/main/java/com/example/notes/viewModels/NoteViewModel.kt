package com.example.notes.viewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.notes.R

class NoteViewModel: BaseViewModel() {

    private val _currentTagType = MutableLiveData(R.drawable.vd_tag_1)

    private val _isNewNote = MutableLiveData(true)

    fun getCurrentTagType(): LiveData<Int> = _currentTagType

    fun getIsNewNote(): LiveData<Boolean> = _isNewNote

    fun setCurrentTagType(resID: Int) {
        _currentTagType.value = resID
    }

    fun setIsNewNote(isNewNote: Boolean) {
        _isNewNote.value = isNewNote
    }
}