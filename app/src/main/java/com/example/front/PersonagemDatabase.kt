package com.example.front

import Atributos.Carisma
import Atributos.Constituicao
import Atributos.Destreza
import Atributos.Forca
import Atributos.Inteligencia
import Atributos.Sabedoria
import androidx.room.Database
import androidx.room.RoomDatabase
import android.content.Context
import androidx.room.Room
import com.example.front.PersonagemDAO

@Database(entities = [Personagem::class, Carisma::class, Constituicao::class, Destreza::class, Forca::class, Inteligencia::class, Sabedoria::class], version = 5, exportSchema = false)
abstract class PersonagemDatabase : RoomDatabase(){

    abstract fun getPersonagemDAO(): PersonagemDAO

    companion object{

        @Volatile
        private var INSTANCE: PersonagemDatabase? = null

        fun getDatabase(context: Context): PersonagemDatabase {
            return INSTANCE ?: synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    PersonagemDatabase::class.java,
                    "personagem_db"
                )
                    .allowMainThreadQueries()
                    .fallbackToDestructiveMigration()
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }

}
