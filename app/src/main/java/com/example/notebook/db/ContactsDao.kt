package com.example.notebook.db

import androidx.room.*
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single

@Dao
interface ContactsDao {
    @Insert(onConflict = OnConflictStrategy.ABORT)
    fun insertContact(contact: Contact): Completable

    @Insert(onConflict = OnConflictStrategy.ABORT)
    fun insertAll(contacts: List<Contact>): Completable

    @Delete
    fun deleteContact(contact: Contact): Completable

    @Update()
    fun updateContact(contact: Contact): Completable

    @Query("SELECT * FROM contacts")
    fun getAllContacts(): Single<List<Contact>>
}