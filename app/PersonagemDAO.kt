package com.example.front.DAO

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.front.Personagem

@Dao
interface PersonagemDAO{

    @Insert
    fun insertPersonagem(personagem: Personagem)

    @Update
    fun updatePersonagem(personagem: Personagem)

    @Delete
    fun deletePersonagem(personagem: Personagem)

    @Query("SELECT * FROM personagem")
    fun getAll(): List<Personagem>

    @Query("SELECT * FROM personagem WHERE id = :id")
    fun getById(id:Int): List<Personagem>
}
