package com.taskdatabase.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.taskdatabase.data.db.UserData

@Database(entities = arrayOf(UserData::class), version = 1)
abstract class UserDatabase: RoomDatabase() {
    abstract fun appDao(): UserDao
    companion object {
        private var instance: UserDatabase? = null
        fun getDBInstant(context: Context) : UserDatabase {
            if (instance == null){
               return buildDatabase(context)
            }else{
                return instance as UserDatabase
            }
        }
        private fun buildDatabase(context: Context) : UserDatabase {
            return Room.databaseBuilder(context,
                UserDatabase::class.java, "todo.db")
                .allowMainThreadQueries()
                .build()
        }
    }
}