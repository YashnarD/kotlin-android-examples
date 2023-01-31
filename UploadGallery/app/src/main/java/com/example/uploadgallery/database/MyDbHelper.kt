package com.example.uploadgallery.database

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.example.uploadgallery.models.Picture

class MyDbHelper(context: Context) :
    SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object {
        const val DATABASE_NAME = "my_db"
        const val DATABASE_VERSION = 1

        const val TABLE_NAME = "picture"
        const val ID = "id"
        const val PATH = "path"
        const val IMAGE = "image"
    }

    override fun onCreate(p0: SQLiteDatabase?) {
        val query =
            "create table $TABLE_NAME ($ID integer primary key autoincrement not null, $PATH text not null, $IMAGE blob not null)"
        p0?.execSQL(query)
    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {
        TODO("Not yet implemented")
    }

    fun addImage(picture: Picture) {
        val database = this.writableDatabase
        val contentValues = ContentValues()
        contentValues.put(PATH, picture.path)
        contentValues.put(IMAGE, picture.image)
        database.insert(TABLE_NAME, null, contentValues)
        database.close()
    }

    fun getAllImages(): List<Picture> {
        val list = ArrayList<Picture>()
        val query = "select * from $TABLE_NAME"
        val database = this.readableDatabase
        val cursor = database.rawQuery(query, null)
        if (cursor.moveToFirst()) {
            do {
                val picture = Picture(cursor.getInt(0), cursor.getString(1), cursor.getBlob(2))
                list.add(picture)
            } while (cursor.moveToNext())
        }
        return list
    }
}