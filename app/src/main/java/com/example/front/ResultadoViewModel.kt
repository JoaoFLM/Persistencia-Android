package com.example.front

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

class ResultadoViewModel(
    val personagem_db: PersonagemDatabase
): ViewModel() {

    val personagemDAO by mutableStateOf(personagem_db.getPersonagemDAO())

    fun insertPersonagem(inserir: Personagem){
        personagemDAO.insertPersonagem(inserir)
    }

    fun getAll(): List<Personagem> {
        return personagemDAO.getAll()
    }

}