package com.example.notes.ViewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class BaseViewModel: ViewModel() {

    private var stateLogIn =  false

    private var _stateLogIn = MutableLiveData(stateLogIn)

    fun getLoginState() : LiveData<Boolean> = _stateLogIn


    fun updateLoginState() {
        stateLogIn = !stateLogIn
        _stateLogIn.value = stateLogIn
    }


}