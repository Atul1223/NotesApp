package com.example.notes.notesData

import android.app.Application
import androidx.room.Room
import org.koin.dsl.module

fun provideDatabase(application: Application): NotesDatabase =
    Room.databaseBuilder(application,NotesDatabase::class.java,"notesdatabase123")
        .fallbackToDestructiveMigration()
        .build()

fun provideDao(db:NotesDatabase):NotesDao = db.dao()

val notesDatabaseModule = module {
    single { provideDatabase(get()) }
    single { provideDao(get()) }
}