package com.example.front.DataBase

import Atributos.Carisma
import Atributos.Constituicao
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface ConstituicaoDAO {

    @Insert
    suspend fun insertConstituicao(atributo: Constituicao)

    @Delete
    suspend fun removeConstituicao(atributo: Constituicao)


    @Query("SELECT * FROM constituição")
    suspend fun selectAll(): List<Constituicao>

}