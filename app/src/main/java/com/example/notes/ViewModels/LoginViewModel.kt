package com.example.notes.ViewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class LoginViewModel: ViewModel() {

    private var loginState = false

    private var _logInState = MutableLiveData(loginState)

    fun getLoginState() : LiveData<Boolean> = _logInState

    fun loginUser() {
        loginState = true
        _logInState.value = loginState
    }
}