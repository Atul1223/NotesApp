package com.example.notes.viewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class LoginViewModel: BaseViewModel() {


    private var _logInState = MutableLiveData(false)

    fun getLoginState() : LiveData<Boolean> = _logInState

    fun loginUser() {
        _logInState.value = true
    }

    fun logOutUser() {
        _logInState.value = false
    }
}