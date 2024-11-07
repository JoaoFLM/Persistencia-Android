import Atributos.*
import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Entity(tableName = "personagem")
@Parcelize
data class Personagem(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    var Nome: String,
    var ForcaPJ: Forca,
    var DestrezaPJ: Destreza,
    var ConstituicaoPJ: Constituicao,
    var InteligenciaPJ: Inteligencia,
    var CarismaPJ: Carisma,
    var SabedoriaPJ: Sabedoria,
    ) : Parcelable {

        fun ComprarAtributos(atributo: Atributo, pontosGastos: Int) : Int {
            return atributo.GastarPontos(pontosGastos)
        }

        fun MostrarAtributos()
        {
            println("## Atributos ##")
            println("Forca: ${ForcaPJ.att}")
            println("Destreza: ${DestrezaPJ.att}")
            println("Constituição: ${ConstituicaoPJ.att}")
            println("Intelingência: ${InteligenciaPJ.att}")
            println("Carisma: ${CarismaPJ.att}")
            println("Sabedoria: ${SabedoriaPJ.att}")
        }

        public fun CalculaPV() : Int? {
            val PVbase = 10
            when (ConstituicaoPJ.att){
                8 -> return PVbase-1
                9 -> return PVbase-1
                10 -> return PVbase+0
                11 -> return PVbase+0
                12 -> return PVbase+1
                13 -> return PVbase+1
                14 -> return PVbase+2
                15 -> return PVbase+2
                16 -> return PVbase+3
                17 -> return PVbase+3
                18 -> return PVbase+4
                19 -> return PVbase+4
                20 -> return PVbase+5
            }
            return null
        }

        private fun CalculaMod(atributo: Atributo) :Int?{
            return when (atributo.getValue()){
                8 -> -1
                9 -> -1
                10 -> 0
                11 -> 0
                12 -> 1
                13 -> 1
                14 -> 2
                15 -> 2
                16 -> 3
                17 -> 3
                18 -> 4
                19 -> 4
                20 -> 5
                else -> {null}
            }
        }

//        fun AdicionaRaca(){
//            this.ForcaPJ.att+=this.Raca.IncrementoForca
//            this.DestrezaPJ.att+=this.Raca.IncrementoDestreza
//            this.ConstituicaoPJ.att+=this.Raca.IncrementoConstituicao
//            this.InteligenciaPJ.att+=this.Raca.IncrementoInteligencia
//            this.SabedoriaPJ.att+=this.Raca.IncrementoSabedoria
//            this.CarismaPJ.att+=this.Raca.IncrementoCarisma
//        }

        fun MostarPersonagem()
        {
            println("## $Nome ##")
            println("Pontos de Vida: ${CalculaPV()}")
            //println("Raca ${Raca.RacaNome}")
            this.MostrarAtributos()

        }
    }