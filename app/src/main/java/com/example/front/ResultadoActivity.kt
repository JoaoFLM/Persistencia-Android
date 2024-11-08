package com.example.front

import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel

class ResultadoActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        try {
            val pj = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                intent.getParcelableExtra("PERSONAGEM", Personagem::class.java)!!
            } else {
                intent.getParcelableExtra("PERSONAGEM")!!
            }

            setContent {
                val personagem_db = PersonagemDatabase.getDatabase(this)
                val viewModel = viewModel<ResultadoViewModel>(
                    factory = object : ViewModelProvider.Factory {
                        override fun <T : ViewModel> create(modelClass: Class<T>): T {
                            return ResultadoViewModel(
                                personagem_db
                            ) as T
                        }
                    }
                )

                viewModel.insertPersonagem(pj)
                val PJLista = viewModel.getAll()
                ResultadoScreen(PJLista, this)
            }
        }catch (e: NullPointerException)
        {
            setContent {
                val personagem_db = PersonagemDatabase.getDatabase(this)
                val viewModel = viewModel<ResultadoViewModel>(
                    factory = object : ViewModelProvider.Factory {
                        override fun <T : ViewModel> create(modelClass: Class<T>): T {
                            return ResultadoViewModel(
                                personagem_db
                            ) as T
                        }
                    }
                )

                val PJLista = viewModel.getAll()
                ResultadoScreen(PJLista, this)
            }
        }
    }
}


@Composable
fun ResultadoScreen(personagens: List<Personagem>, context: Context) {
    Row(modifier = Modifier.padding(16.dp)) {
        // Primeiro Column
        Column(modifier = Modifier.weight(1f)) {
            Text(text = "Lista de Personagens")

            LazyColumn {
                items(personagens) { personagem ->
                    Card(modifier = Modifier.padding(8.dp)) {
                        Column(modifier = Modifier.padding(16.dp)) {
                            Text(text = ""
                                    + personagem.PersonagemId + "\n"
                                    + personagem.nome + "\n"
                                    + personagem.forca + "\n"
                                    + personagem.destreza + "\n"
                                    + personagem.constituicao + "\n"
                                    + personagem.inteligencia + "\n"
                                    + personagem.sabedoria + "\n"
                                    + personagem.carisma)
                        }
                    }
                }
            }
        }

        // Segundo Column
        Column(modifier = Modifier.weight(1f).padding(start = 16.dp)) {
            Button(onClick = {
                val intent = Intent(context, DeletarPersonagemActivity::class.java).apply {}
                context.startActivity(intent)
            }) { Text(text = "Deletar") }
        }
    }
}

