package com.example.sqlite.database

import com.example.sqlite.models.Contact

interface DatabaseService {

    fun addContact(contact: Contact)

    fun getAllContacts(): List<Contact>

    fun editContact(contact: Contact)

    fun deleteContact(contact: Contact)

    fun getContactById(id: Int): Contact

    fun getContactsCount(): Int

    fun deleteAll()
}