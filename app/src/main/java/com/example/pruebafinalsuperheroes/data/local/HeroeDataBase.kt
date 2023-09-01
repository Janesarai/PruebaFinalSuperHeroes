package com.example.pruebafinalsuperheroes.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [HeroeEntity::class, HeroeDetalleEntity::class], version =1)

abstract class HeroeDataBase: RoomDatabase (){

    abstract fun getHeroeDao(): HeroeDAO

    companion object {
        @Volatile
        private var INSTANCE: HeroeDataBase? = null

        fun getDatabase(context: Context): HeroeDataBase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    HeroeDataBase::class.java,
                    "heroes_database"
                ).build()

                INSTANCE = instance
                return instance
            }
        }
    }
}