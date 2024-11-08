package com.example.front

import Personagem
import android.content.Intent
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import com.example.front.DAO.PersonagemDatabase

class ResultadoActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        try {
            val personagem = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                intent.getParcelableExtra("PERSONAGEM", Personagem::class.java)!!
            } else {
                intent.getParcelableExtra("PERSONAGEM")!!
            }

            try{
                val db = PersonagemDatabase.getDatabase(this)
                val personagemDAO = db.getPersonagemDAO()
            }catch(e: NullPointerException){
                setContent{
                    val context = LocalContext.current
                    Text(text = "Erro ao carregar o banco de dados")
                    Button(onClick = {
                        val intent = Intent(this, MainActivity::class.java).apply {}
                        context.startActivity(intent)
                    }) {
                        Text(text = "Voltar")
                    }
                }
            }
            setContent {
                ResultadoScreen(personagem)
            }
        }catch (e: NullPointerException)
        {
            setContent{
                val context = LocalContext.current
                Text(text = "Personagem invalido")
                Button(onClick = {
                    val intent = Intent(this, MainActivity::class.java).apply {}
                    context.startActivity(intent)
                }) {
                    Text(text = "Voltar")
                }
            }
        }
    }
}


@Composable
fun ResultadoScreen(personagem: Personagem?) {
//    if (personagem != null) {
//        Column {
//            Text(text = "Nome: ${personagem.Nome}")
//            Text(text = "Força: ${personagem.ForcaPJ.getValue()}")
//            Text(text = "Destreza: ${personagem.DestrezaPJ.getValue()}")
//            Text(text = "Constituição: ${personagem.ConstituicaoPJ.getValue()}")
//            Text(text = "Sabedoria: ${personagem.SabedoriaPJ.getValue()}")
//            Text(text = "Inteligência: ${personagem.InteligenciaPJ.getValue()}")
//            Text(text = "Carisma: ${personagem.CarismaPJ.getValue()}")
//            Text(text = "Pontos de vida: ${personagem.CalculaPV()}")
//        }
//    } else {
//        Text(text = "Nenhum personagem recebido.")
//    }
}
