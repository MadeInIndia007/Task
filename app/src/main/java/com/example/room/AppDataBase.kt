package com.example.moviesdemo.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.room.Enitity
import com.example.room.MyTypeConverters

@Database(entities = [Enitity::class], version = 3,exportSchema = false)
@TypeConverters(MyTypeConverters::class)
abstract class AppDataBase : RoomDatabase() {
    abstract fun movieDao(): Dao

    companion object {
        @Volatile
        private var INSTANCE: AppDataBase? = null

        fun getInstance(context: Context): AppDataBase {
            if (INSTANCE == null) {
                synchronized(AppDataBase::class) {
                    INSTANCE = Room.databaseBuilder(
                        context.applicationContext,
                        AppDataBase::class.java,
                        "m-db"

                    ).build()
                }
            }
            return INSTANCE!!
        }
    }

    fun destroyInstance() {
        INSTANCE = null
    }
}
