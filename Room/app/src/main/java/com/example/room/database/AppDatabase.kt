package com.example.room.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.room.dao.ContactDao
import com.example.room.entities.Contact

@Database(entities = [Contact::class], version = 2)
abstract class AppDatabase: RoomDatabase() {

    abstract fun contactDao(): ContactDao

    companion object {
        private var instance: AppDatabase? = null

        @Synchronized
        fun getInstance(context: Context): AppDatabase {
            if(instance == null) {
                instance = Room.databaseBuilder(context, AppDatabase::class.java, "my_db")
                    .addMigrations(MIGRATION_1_2)
                    .fallbackToDestructiveMigration()
                    .allowMainThreadQueries()
                    .build()
            }
            return instance!!
        }

        val MIGRATION_1_2 = object : Migration(1,2) {
            override fun migrate(database: SupportSQLiteDatabase) {
                database.execSQL("ALTER TABLE 'contact' ADD COLUMN 'age' INTEGER NOT NULL DEFAULT 0")
            }

        }
    }
}