package com.example.ec_final2.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.ec_final2.model.Cafe


@Database(entities = [Cafe::class], version = 1)
abstract class CafeDataBase: RoomDatabase() {
    abstract fun cafeDao(): CafeDao

    companion object{
        @Volatile
        private var instance: CafeDataBase? = null
        fun getDatabase(context: Context): CafeDataBase {
            val tempInstance = instance
            if (tempInstance !=null) {
                return tempInstance
            }
            synchronized(this) {
                val _instance = Room.databaseBuilder(
                    context.applicationContext,
                    CafeDataBase::class.java,
                    "cafeDB").build()
                instance = _instance
                return _instance
            }

        }
    }
}