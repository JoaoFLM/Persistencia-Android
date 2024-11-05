package com.example.front.DataBase

import Atributos.Carisma
import Atributos.Inteligencia
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface InteligenciaDAO {

    @Insert
    suspend fun insertInteligencia(atributo: Inteligencia)

    @Delete
    suspend fun removeInteligencia(atributo: Inteligencia)


    @Query("SELECT * FROM inteligencia")
    suspend fun selectAll(): List<Inteligencia>

}