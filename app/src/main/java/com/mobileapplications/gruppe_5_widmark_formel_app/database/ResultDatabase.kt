package com.mobileapplications.gruppe_5_widmark_formel_app.database

import android.content.Context
import android.provider.ContactsContract
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [ContactsContract.CommonDataKinds.Note::class], version = 1, exportSchema = false)
abstract class ResultDatabase: RoomDatabase() {
    abstract val resultDao: ResultDao

    companion object {
        @Volatile
        private var INSTANCE: ResultDatabase? = null

        fun getInstance(context: Context): ResultDatabase{
            synchronized(this){
                var instance = INSTANCE

                if(instance == null){
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        ResultDatabase::class.java,
                        "note_database"
                    ).fallbackToDestructiveMigration()
                        .build()
                }
                return instance
            }
        }
    }
}