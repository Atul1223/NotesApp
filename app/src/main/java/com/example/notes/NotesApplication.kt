package com.example.notes

import android.app.Application
import android.util.Log
import com.example.notes.di.appModule
import com.example.notes.notesData.notesDatabaseModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class NotesApplication: Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(this@NotesApplication)
            modules(notesDatabaseModule, appModule)
        }

        Log.d("debug--","${notesDatabaseModule.isLoaded}")
    }
}