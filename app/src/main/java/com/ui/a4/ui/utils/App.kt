package com.ui.a4.ui.utils

import android.app.Application
import android.content.SharedPreferences
import com.ui.a4.data.NoteDatabase


class App : Application(){
    private lateinit var preferences: SharedPreferences

    companion object{
        lateinit var db: NoteDatabase
        lateinit var prefs: Prefs
    }

    override fun onCreate() {
        super.onCreate()
        preferences = this.applicationContext
            .getSharedPreferences("settings", MODE_PRIVATE)
        prefs = Prefs(preferences)
        db = NoteDatabase.invoke(this)
    }
}