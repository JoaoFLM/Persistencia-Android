package com.example.front.DAO

import Personagem
import androidx.room.Database
import androidx.room.RoomDatabase
import android.content.Context
import androidx.room.Room

@Database(entities = [Personagem::class], version = 2, exportSchema = false)
abstract class PersonagemDatabase : RoomDatabase(){

    abstract fun getPersonagemDAO():PersonagemDAO

    companion object{
        private var INSTANCE: PersonagemDatabase? = null

        fun getDatabase(context: Context): PersonagemDatabase{
            return INSTANCE ?: synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    PersonagemDatabase::class.java,
                    "personagem_db"
                )
                    .fallbackToDestructiveMigration()
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }

}
