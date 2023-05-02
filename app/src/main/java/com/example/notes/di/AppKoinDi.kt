package com.example.notes.di

import com.example.notes.viewModels.HomeViewModel
import com.example.notes.viewModels.LoginViewModel
import com.example.notes.viewModels.NoteViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    viewModel{ HomeViewModel() }
    viewModel { LoginViewModel() }
    viewModel { NoteViewModel(get()) }
}