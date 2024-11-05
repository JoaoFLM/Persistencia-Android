package com.example.front.DataBase

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.front.Personagem

@Dao
interface PersonagemDAO {


    @Insert
    suspend fun insertPersonagem(personagem: Personagem)

    @Insert
    suspend fun insertPersonagens(personagens: List<Personagem>)

    @Query("SELECT * FROM personagem")
    suspend fun getAllPersonagens(): List<Personagem>
}
