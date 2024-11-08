package com.example.front

import Atributos.Atributo
import Atributos.Carisma
import Atributos.Constituicao
import Atributos.Destreza
import Atributos.Forca
import Atributos.Inteligencia
import Atributos.Sabedoria
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Snackbar
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat.startActivity


class MainActivity : ComponentActivity() {
    private var carisma: Carisma = Carisma()
    private var constituicao: Constituicao = Constituicao()
    private var destreza: Destreza = Destreza()
    private var forca: Forca = Forca()
    private var inteligencia: Inteligencia = Inteligencia()
    private var sabedoria: Sabedoria = Sabedoria()
    private var pontos: Int = 27

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AtributosScreen(
                carisma,
                constituicao,
                destreza,
                forca,
                inteligencia,
                sabedoria,
                pontos,
            )
        }
    }
}

@Composable
fun AtributosScreen(
    car: Carisma,
    con: Constituicao,
    des: Destreza,
    forca: Forca,
    int: Inteligencia,
    sab: Sabedoria,
    pontos: Int,
) {
    var pontosRestantes by remember { mutableStateOf(pontos) }
    var snackbarVisible by remember { mutableStateOf(false) }
    var snackbarMessage by remember { mutableStateOf("") }
    val context = LocalContext.current

    Column(modifier = Modifier.padding(16.dp)) {

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(text = "Criação de Personagem", fontSize = 18.sp)

            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(text = "Pontos restantes:", fontSize = 16.sp)
                Spacer(modifier = Modifier.width(8.dp))
                Text(text = "$pontosRestantes", fontSize = 16.sp)
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(text = "Atributo", modifier = Modifier.weight(1f))
            Text(
                text = "Valor",
                modifier = Modifier.weight(1f),
                textAlign = androidx.compose.ui.text.style.TextAlign.Center
            )
            Text(
                text = "Raça",
                modifier = Modifier.weight(1f),
                textAlign = androidx.compose.ui.text.style.TextAlign.Center
            )
            Text(
                text = "Mod",
                modifier = Modifier.weight(1f),
                textAlign = androidx.compose.ui.text.style.TextAlign.Center
            )
        }

        Spacer(modifier = Modifier.height(8.dp))

        AtributoInputRow(
            "Força",
            forca,
            { pontosRestantes -= it },
            { showMessage(it, { snackbarMessage = it; snackbarVisible = true }) })
        Spacer(modifier = Modifier.height(8.dp))
        AtributoInputRow(
            "Destreza",
            des,
            { pontosRestantes -= it },
            { showMessage(it, { snackbarMessage = it; snackbarVisible = true }) })
        Spacer(modifier = Modifier.height(8.dp))
        AtributoInputRow(
            "Constituição",
            con,
            { pontosRestantes -= it },
            { showMessage(it, { snackbarMessage = it; snackbarVisible = true }) })
        Spacer(modifier = Modifier.height(8.dp))
        AtributoInputRow(
            "Sabedoria",
            sab,
            { pontosRestantes -= it },
            { showMessage(it, { snackbarMessage = it; snackbarVisible = true }) })
        Spacer(modifier = Modifier.height(8.dp))
        AtributoInputRow(
            "Inteligência",
            int,
            { pontosRestantes -= it },
            { showMessage(it, { snackbarMessage = it; snackbarVisible = true }) })
        Spacer(modifier = Modifier.height(8.dp))
        AtributoInputRow(
            "Carisma",
            car,
            { pontosRestantes -= it },
            { showMessage(it, { snackbarMessage = it; snackbarVisible = true }) })

        if (snackbarVisible) {
            Snackbar(
                action = {
                    Button(onClick = { snackbarVisible = false }) {
                        Text("Fechar")
                    }
                },
                modifier = Modifier.padding(8.dp)
            ) {
                Text(snackbarMessage)
            }
        }

        Button(onClick = {
            val personagem = Personagem(
                0,
                "Placeholder",
                forca.ForcaId,
                des.DestrezaId,
                con.ConstituicaoId,
                int.InteligenciaId,
                car.CarismaId,
                sab.SabedoriaId
            )

            val intent = Intent(context, ResultadoActivity::class.java).apply {
                putExtra("PERSONAGEM", personagem)
            }
            context.startActivity(intent)

        }) {
            Text("Salvar atributos")
        }
    }
}


@Composable
fun AtributoInputRow(
    label: String,
    atributo: Atributo,
    updatePontos: (Int) -> Unit,
    onError: (String) -> Unit
) {
    var attValue by remember { mutableStateOf("") }
    var racaValue by remember { mutableStateOf("") }

    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(text = label, modifier = Modifier.weight(1f))

        OutlinedTextField(
            value = attValue,
            onValueChange = { newValue ->
                if (newValue.all { it.isDigit() }) {
                    attValue = newValue
                }
            },
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Number
            ),
            modifier = Modifier
                .weight(1f)
                .width(50.dp)
                .onFocusChanged { focusState ->
                    if (!focusState.isFocused && attValue.isNotEmpty()) {
                        try {
                            val valor = attValue.toInt()
                            updatePontos(atributo.GastarPontos(valor))
                        } catch (e: NumberFormatException) {
                            onError("Por favor, insira um número válido.")
                            attValue = ""
                        } catch(e: IllegalArgumentException){
                            onError("O valor deve estar entre 8 e 15")
                            attValue = ""
                        }
                    }
                }
        )

        OutlinedTextField(
            value = racaValue,
            onValueChange = { newRaca ->
                if (newRaca.all { it.isDigit() }) {
                    racaValue = newRaca
                }
            },
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Number
            ),
            modifier = Modifier
                .weight(1f)
                .width(50.dp)
                .onFocusChanged { focusState ->
                    if (!focusState.isFocused && racaValue.isNotEmpty()) {
                        try {
                            val valor = racaValue.toInt()
                            atributo.addRaca(valor)
                        } catch (e: NumberFormatException) {
                            onError("Por favor, insira um número válido.")
                            racaValue = ""
                        } catch(e: IllegalArgumentException){
                            onError("O valor deve estar entre -1 e 2")
                            racaValue = ""
                        }
                    }
                }
        )

        Text(
            text = "${calculaMod(atributo)}",
            modifier = Modifier.weight(1f),
            textAlign = androidx.compose.ui.text.style.TextAlign.Center
        )
    }
}

fun showMessage(message: String, callback: (String) -> Unit) {
    callback(message)
}


fun calculaMod(atributo: Atributo) :Int? {
    return when (atributo.getValue()) {
        8,9 -> -1
        10,11 -> 0
        12,13 -> 1
        14,15 -> 2
        16,17 -> 3
        18,19 -> 4
        20 -> 5
        else -> null
    }
}



