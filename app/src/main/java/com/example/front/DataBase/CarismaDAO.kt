package com.example.front.DataBase

import Atributos.Carisma
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface CarismaDAO {

    @Insert
    suspend fun insertCarisma(atributo: Carisma)

    @Delete
    suspend fun removeCarisma(atributo: Carisma)


    @Query("SELECT * FROM carisma")
    suspend fun selectAll(): List<Carisma>
}