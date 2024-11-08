package com.example.front

import android.content.Intent
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
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

            try{
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
                    ResultadoScreen(PJLista)
                }
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
fun ResultadoScreen(personagens: List<Personagem>) {
    Column(modifier = Modifier.padding(16.dp)) {
        Text(text = "Lista de Personagens")

        LazyColumn {
            items(personagens) { personagem ->
                Card(modifier = Modifier.padding(8.dp)) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Text(text = personagem.nome)
                    }
                }
            }
        }
    }
}

