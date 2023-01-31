package com.example.sqlite.database

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.example.sqlite.models.Contact

class MyDbHelper(context: Context) :
    SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION), DatabaseService {

    companion object {
        val DATABASE_NAME = "g21"
        val DATABASE_VERSION = 1

        val TABLE_NAME = "contact"
        val ID = "id"
        val NAME = "name"
        val NUMBER = "number"
    }

    override fun onCreate(db: SQLiteDatabase?) {
        val query =
            "create table $TABLE_NAME ($ID integer not null primary key autoincrement, $NAME text not null, $NUMBER text not null)"
        db?.execSQL(query)
    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {

    }

    override fun addContact(contact: Contact) {
        val database = this.writableDatabase
        val contentValues = ContentValues()
        contentValues.put(NAME, contact.name)
        contentValues.put(NUMBER, contact.number)
        database.insert(TABLE_NAME, null, contentValues)
        database.close()
    }

    override fun getAllContacts(): List<Contact> {
        val list = ArrayList<Contact>()
        val database = this.readableDatabase
        val query = "select * from $TABLE_NAME"
        val cursor = database.rawQuery(query, null)

        if (cursor.moveToFirst()) {
            do {
                val id = cursor.getInt(0)
                val name = cursor.getString(1)
                val number = cursor.getString(2)
                val contact = Contact(id, name, number)
                list.add(contact)
            } while (cursor.moveToNext())
        }
        return list
    }

    override fun editContact(contact: Contact) {
        val database = this.writableDatabase
        val contentValues = ContentValues()
        contentValues.put(ID, contact.id)
        contentValues.put(NAME, contact.name)
        contentValues.put(NUMBER, contact.number)
        database.update(TABLE_NAME, contentValues, "$ID = ?", arrayOf(contact.id.toString()))
        database.close()
    }

    override fun deleteContact(contact: Contact) {
        val database = this.writableDatabase
        database.delete(TABLE_NAME, "$ID = ?", arrayOf(contact.id.toString()))
        database.close()
    }

    override fun getContactById(id: Int): Contact {
        TODO("Not yet implemented")
    }

    override fun getContactsCount(): Int {
        TODO("Not yet implemented")
    }

    override fun deleteAll() {
        TODO("Not yet implemented")
    }
}
