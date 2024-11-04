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

        val personagem: Personagem? = intent.getParcelableExtra<Personagem>("PERSONAGEM")

        setContent {
            ResultadoScreen(personagem)
        }
    }
}

@Composable
fun ResultadoScreen(personagem: Personagem?) {
    if (personagem != null) {
        Column {
            Text(text = "Nome: ${personagem.Nome}")
            Text(text = "Força: ${personagem.ForcaPJ.getValue()}")
            Text(text = "Destreza: ${personagem.DestrezaPJ.getValue()}")
            Text(text = "Constituição: ${personagem.ConstituicaoPJ.getValue()}")
            Text(text = "Sabedoria: ${personagem.SabedoriaPJ.getValue()}")
            Text(text = "Inteligência: ${personagem.InteligenciaPJ.getValue()}")
            Text(text = "Carisma: ${personagem.CarismaPJ.getValue()}")
            Text(text = "Pontos de vida: ${personagem.CalculaPV()}")
        }
    } else {
        Text(text = "Nenhum personagem recebido.")
    }
}
