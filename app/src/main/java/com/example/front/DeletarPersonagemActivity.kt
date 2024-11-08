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
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel

class DeletarPersonagemActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
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
            deletarScreen(this, viewModel)
        }
    }
}

@Composable
fun scancId(onNumberEntered: (Int?) -> Unit, context: Context) {
    var numberInput by remember { mutableStateOf(TextFieldValue()) }
    var errorMessage by remember { mutableStateOf<String?>(null) }

    Column(
        modifier = Modifier.padding(16.dp)
    ) {
        OutlinedTextField(
            value = numberInput,
            onValueChange = { numberInput = it },
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Number
            ),
            label = { Text("Digite um número") },
            modifier = Modifier.padding(bottom = 16.dp)
        )
        Row(modifier = Modifier.padding(32.dp)) {
            Button(
                onClick = {
                    val parsedNumber = processNumber(numberInput.text)
                    if (parsedNumber != null) {
                        onNumberEntered(parsedNumber)
                        voltar(context)
                    }
                },
                modifier = Modifier.padding(bottom = 16.dp)
            ) {
                Text("Enviar")
            }

            Button(
                onClick = {
                    voltar(context)
                },
                modifier = Modifier.weight(1f).padding(end = 8.dp)
            ) {
                Text(text = "Voltar")
            }
        }

        errorMessage?.let {
            Text(text = it)
        }
    }
}

fun processNumber(input: String): Int? {
    return try {
        input.toInt()
    } catch (e: NumberFormatException) {
        null
    }
}

@Composable
fun deletarScreen(context: Context, viewModel: ResultadoViewModel){
    scancId( { result ->
        if (result != null) {
            viewModel.deletarPersonagem(result)
        }
    }, context)
}

fun voltar(context: Context){
    val intent = Intent(context, ResultadoActivity::class.java).apply {}
    context.startActivity(intent)
}







