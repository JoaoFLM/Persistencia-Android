package com.example.front

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface PersonagemDAO {

    @Insert
    fun insertPersonagem(personagem: Personagem)

    @Delete
    fun deletePersonagem(personagem: Personagem)

    @Update
    fun updatePersonagem(personagem: Personagem)

    @Query("SELECT * FROM personagem")
    fun getAll(): List<Personagem>

    @Query("SELECT * FROM personagem WHERE PersonagemId = :id")
    fun getPersonagemById(id: Int) : Personagem

}
