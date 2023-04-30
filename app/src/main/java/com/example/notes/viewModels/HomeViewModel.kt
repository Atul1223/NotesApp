package com.example.notes.viewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlin.random.Random

class HomeViewModel: BaseViewModel() {

    private val _notesListSize = MutableLiveData(0)

    fun getNotesListSize() : LiveData<Int> = _notesListSize

    fun addToNotesList() {
            _notesListSize.value = _notesListSize.value?.plus(1)
    }

    fun removeFromNotesList() {
        _notesListSize.value = _notesListSize.value?.minus(1)
    }

    fun randomNumGenerator() : Int{
        return Random.nextInt(0,5)
    }
}