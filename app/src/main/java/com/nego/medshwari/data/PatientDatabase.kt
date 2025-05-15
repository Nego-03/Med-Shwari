package com.nego.medshwari.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.nego.medshwari.model.Patient
import com.nego.medshwari.model.User

import kotlin.jvm.java

@Database(entities = [Patient::class, User::class], version = 3, exportSchema = false)
abstract class PatientDatabase : RoomDatabase() {
    abstract fun PatientDao(): PatientDao
    abstract fun userDao(): UserDao

    companion object {
        @Volatile
        private var INSTANCE:PatientDatabase? = null

        fun getDatabase(context: Context): PatientDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    PatientDatabase::class.java,
                    "main_database"
                )
                    .fallbackToDestructiveMigration() // 💥 This clears DB on version change
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }
}