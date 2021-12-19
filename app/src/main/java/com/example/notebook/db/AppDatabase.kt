package com.example.notebook.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@Database(entities = [Contact::class], version = AppDatabase.VERSION, exportSchema = false)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {

    companion object {
        const val DB_NAME = "notebook.db"
        const val VERSION = 1
    }

    abstract fun contactsDao(): ContactsDao
}