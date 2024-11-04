package com.example.front

import Personagem
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

class ResultadoActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val personagem: Personagem? = intent.getParcelableExtra("PERSONAGEM")

        setContent {
            ResultadoScreen(personagem)
        }
    }
}

@Composable
fun ResultadoScreen(personagem: Personagem?) {
    if (personagem != null) {
        Column {
            Text(text = "Nome: ${personagem.nome}")
            Text(text = "Força: ${personagem.forcaPJ.getValue()}")
            Text(text = "Destreza: ${personagem.destrezaPJ.getValue()}")
            Text(text = "Constituição: ${personagem.constituicaoPJ.getValue()}")
            Text(text = "Sabedoria: ${personagem.sabedoriaPJ.getValue()}")
            Text(text = "Inteligência: ${personagem.inteligenciaPJ.getValue()}")
            Text(text = "Carisma: ${personagem.carismaPJ.getValue()}")
            Text(text = "Pontos de vida: ${personagem.calculaPV()}")
        }
    } else {
        Text(text = "Nenhum personagem recebido.")
    }
}
