package com.example.front.DataBase

import Atributos.Carisma
import Atributos.Forca
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface ForcaDAO {

    @Insert
    suspend fun insertForca(atributo: Forca)

    @Delete
    suspend fun removeForca(atributo: Forca)


    @Query("SELECT * FROM forca")
    suspend fun selectAll(): List<Forca>
}