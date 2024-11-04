package com.example.front.DataBase

import Personagem
import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Personagem::class], version = 1)
abstract class AppDataBase : RoomDatabase() {
    abstract fun personagemDAO(): PersonagemDAO

    companion object {
        @Volatile
        private var INSTANCE:AppDataBase? = null

        fun getDatabase(context: Context): AppDataBase{
            return INSTANCE?: synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDataBase::class.java,
                    "app_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}