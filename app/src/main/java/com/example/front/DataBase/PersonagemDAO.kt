package com.example.front.DataBase

import Personagem
import androidx.room.Dao
import androidx.room.Insert

@Dao
interface PersonagemDAO {
    @Insert
    suspend fun insert(personagem: Personagem)
}