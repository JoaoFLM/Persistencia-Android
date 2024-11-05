package com.example.front.DataBase

import Atributos.Carisma
import Atributos.Sabedoria
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface SabedoriaDAO {

    @Insert
    suspend fun insertSabedoria(atributo: Sabedoria)

    @Delete
    suspend fun removeSabedoria(atributo: Sabedoria)

    @Query("SELECT * FROM sabedoria")
    suspend fun selectAll(): List<Sabedoria>
}