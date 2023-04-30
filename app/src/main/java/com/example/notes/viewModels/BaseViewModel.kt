package com.example.notes.viewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

open class BaseViewModel: ViewModel() {

//    private var stateLogIn =  false
//
//    private var _stateLogIn = MutableLiveData(stateLogIn)
//
//    fun getLoginState() : LiveData<Boolean> = _stateLogIn
//
//
//    fun updateLoginState() {
//        CoroutineScope(Dispatchers.Unconfined).launch {
//            stateLogIn = !stateLogIn
//            _stateLogIn.value = stateLogIn
//        }
//    }


}