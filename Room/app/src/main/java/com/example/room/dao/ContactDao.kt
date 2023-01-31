package com.example.room.dao

import androidx.room.*
import androidx.room.OnConflictStrategy.REPLACE
import com.example.room.entities.Contact

@Dao
interface ContactDao {

    @Insert
    fun addContact(contact: Contact)

    @Insert
    fun addContacts1(vararg contact: Contact)

    @Insert(onConflict = REPLACE)
    fun addContacts2(list: List<Contact>)

    @Update
    fun editContact(contact: Contact)

    @Delete
    fun deleteContact(contact: Contact)

    @Query("select * from contact")
    fun getAllContacts(): List<Contact>

    @Query("select * from contact where id=:id")
    fun getContactById(id: Int): Contact

    @Query("select * from contact where name like '%' || :name || '%'")
    fun getContactFilter(name: String): List<Contact>
}