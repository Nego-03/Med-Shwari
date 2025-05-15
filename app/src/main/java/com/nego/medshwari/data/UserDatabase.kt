package com.nego.medshwari.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.nego.medshwari.model.Patient
import com.nego.medshwari.model.User

@Database(entities = [Patient::class, User::class], version = 3, exportSchema = false)
abstract class UserDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao

    companion object {
        @Volatile
        private var INSTANCE: UserDatabase? = null

        fun getDatabase(context: Context): UserDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    UserDatabase::class.java,
                    "user_database"
                )
                    .fallbackToDestructiveMigration() // DANGEROUS IN PRODUCTION, OK FOR NOW
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }
}