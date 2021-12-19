package com.example.notebook.repository

import com.example.notebook.db.Contact
import com.example.notebook.db.ContactsDao
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class Repository @Inject constructor(private val contactsDao: ContactsDao) {
    fun insertContact(contact: Contact) = contactsDao.insertContact(contact)
    fun getAllContacts() = contactsDao.getAllContacts()
    fun updateContact(contact: Contact) = contactsDao.updateContact(contact)
    fun deleteContact(contact: Contact) = contactsDao.deleteContact(contact)
    fun insertAll(contacts: List<Contact>) = contactsDao.insertAll(contacts)
}