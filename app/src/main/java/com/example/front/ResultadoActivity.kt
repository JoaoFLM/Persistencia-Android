package com.example.front

import Atributos.Carisma
import Atributos.Constituicao
import Atributos.Destreza
import Atributos.Forca
import Atributos.Inteligencia
import Atributos.Sabedoria
import DestrezaDAO
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.BasicText
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.front.DataBase.CarismaDAO
import com.example.front.DataBase.ConstituicaoDAO
import com.example.front.DataBase.ForcaDAO
import com.example.front.DataBase.InteligenciaDAO
import com.example.front.DataBase.PersonagemDAO
import com.example.front.DataBase.SabedoriaDAO
import kotlinx.coroutines.launch

class ResultadoActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        try {
            var db = AppDataBase.getDatabase(this)

            val personagemDAO = db.personagemDAO()
            val carismaDAO = db.CarismaDAO()
            val constituicaoDAO = db.ConstituicaoDAO()
            val destrezaDAO = db.DestrezaDAO()
            val forcaDAO = db.ForcaDAO()
            val inteligenciaDAO = db.InteligenciaDAO()
            val sabedoriaDAO = db.SabedoriaDAO()

            // Recuperando os dados passados para a activity
            val personagem: Personagem? = intent.getParcelableExtra("PERSONAGEM")
            val carisma: Carisma? = intent.getParcelableExtra("CARISMA")
            val constituicao: Constituicao? = intent.getParcelableExtra("CONSTITUICAO")
            val destreza: Destreza? = intent.getParcelableExtra("DESTREZA")
            val forca: Forca? = intent.getParcelableExtra("FORCA")
            val inteligencia: Inteligencia? = intent.getParcelableExtra("INTELIGENCIA")
            val sabedoria: Sabedoria? = intent.getParcelableExtra("SABEDORIA")

            setContent {
                if (personagem != null && carisma != null && constituicao != null && destreza != null && forca != null && inteligencia != null && sabedoria != null) {
                    ResultadoScreen(
                        personagem,
                        carisma,
                        constituicao,
                        destreza,
                        forca,
                        inteligencia,
                        sabedoria,
                        personagemDAO,
                        carismaDAO,
                        constituicaoDAO,
                        destrezaDAO,
                        forcaDAO,
                        inteligenciaDAO,
                        sabedoriaDAO
                    )
                } else {
                    Text("Dados do personagem ou atributos não encontrados.")
                }
            }
        }
        catch (e: Exception){
            Log.e("Erro", "BANCO DE DADOS CONTINUA RETORNANDO NULO")

        }
    }
}

@Composable
fun ResultadoScreen(
    personagem: Personagem,
    carisma: Carisma,
    constituicao: Constituicao,
    destreza: Destreza,
    forca: Forca,
    inteligencia: Inteligencia,
    sabedoria: Sabedoria,
    personagemDAO: PersonagemDAO,
    carismaDAO: CarismaDAO,
    constituicaoDAO: ConstituicaoDAO,
    destrezaDAO: DestrezaDAO,
    forcaDAO: ForcaDAO,
    inteligenciaDAO: InteligenciaDAO,
    sabedoriaDAO: SabedoriaDAO
) {
    var loading by remember { mutableStateOf(true) }
    var successMessage by remember { mutableStateOf("") }
    val coroutineScope = rememberCoroutineScope()

    // Inserindo os dados no banco de dados em uma coroutine
    LaunchedEffect(Unit) {
        coroutineScope.launch {
            try {
                // Inserção no banco de dados
                carismaDAO.insertCarisma(carisma)
                constituicaoDAO.insertConstituicao(constituicao)
                destrezaDAO.insertDestreza(destreza)
                forcaDAO.insertForca(forca)
                inteligenciaDAO.insertInteligencia(inteligencia)
                sabedoriaDAO.insertSabedoria(sabedoria)
                personagemDAO.insertPersonagem(personagem)

                successMessage = "Dados inseridos com sucesso!"
            } catch (e: Exception) {
                successMessage = "Erro ao inserir os dados: ${e.message}"
            } finally {
                loading = false
            }
        }
    }

    // Layout do Compose para exibição
    Column(modifier = Modifier.padding(16.dp)) {
        if (loading) {
            Text(text = "Carregando dados...")
        } else {
            Text(text = successMessage)

            // Exibindo os dados do personagem e atributos
            Text(text = "Personagem: ${personagem.nome}")
            Text(text = "Força: ${forca.att}")
            Text(text = "Destreza: ${destreza.att}")
            Text(text = "Constituição: ${constituicao.att}")
            Text(text = "Inteligência: ${inteligencia.att}")
            Text(text = "Sabedoria: ${sabedoria.att}")
            Text(text = "Carisma: ${carisma.att}")

            // Botão para sair da tela ou continuar a navegação
            Button(onClick = { }) {
                Text("Voltar")
            }
        }
    }
}
