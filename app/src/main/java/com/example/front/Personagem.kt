import Atributos.*
import android.os.Parcel
import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName="personagem")
class Personagem(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    var nome: String,
    var forcaPJ: Forca,
    var destrezaPJ: Destreza,
    var constituicaoPJ: Constituicao,
    var inteligenciaPJ: Inteligencia,
    var carismaPJ: Carisma,
    var sabedoriaPJ: Sabedoria
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readString() ?: "",
        parcel.readParcelable(Forca::class.java.classLoader) ?: Forca(),
        parcel.readParcelable(Destreza::class.java.classLoader) ?: Destreza(),
        parcel.readParcelable(Constituicao::class.java.classLoader) ?: Constituicao(),
        parcel.readParcelable(Inteligencia::class.java.classLoader) ?: Inteligencia(),
        parcel.readParcelable(Carisma::class.java.classLoader) ?: Carisma(),
        parcel.readParcelable(Sabedoria::class.java.classLoader) ?: Sabedoria()
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(id)
        parcel.writeString(nome)
        parcel.writeParcelable(forcaPJ, flags)
        parcel.writeParcelable(destrezaPJ, flags)
        parcel.writeParcelable(constituicaoPJ, flags)
        parcel.writeParcelable(inteligenciaPJ, flags)
        parcel.writeParcelable(carismaPJ, flags)
        parcel.writeParcelable(sabedoriaPJ, flags)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Personagem> {
        override fun createFromParcel(parcel: Parcel): Personagem {
            return Personagem(parcel)
        }

        override fun newArray(size: Int): Array<Personagem?> {
            return arrayOfNulls(size)
        }
    }

    fun comprarAtributos(atributo: Atributo, pontosGastos: Int): Int {
        return atributo.GastarPontos(pontosGastos)
    }

    fun mostrarAtributos() {
        println("## Atributos ##")
        println("Força: ${forcaPJ.att}")
        println("Destreza: ${destrezaPJ.att}")
        println("Constituição: ${constituicaoPJ.att}")
        println("Inteligência: ${inteligenciaPJ.att}")
        println("Carisma: ${carismaPJ.att}")
        println("Sabedoria: ${sabedoriaPJ.att}")
    }

    fun calculaPV(): Int? {
        val pvBase = 10
        return when (constituicaoPJ.att) {
            8, 9 -> pvBase - 1
            10, 11 -> pvBase
            12, 13 -> pvBase + 1
            14, 15 -> pvBase + 2
            16, 17 -> pvBase + 3
            18, 19 -> pvBase + 4
            20 -> pvBase + 5
            else -> null
        }
    }

    private fun calculaMod(atributo: Atributo): Int? {
        return when (atributo.getValue()) {
            8, 9 -> -1
            10, 11 -> 0
            12, 13 -> 1
            14, 15 -> 2
            16, 17 -> 3
            18, 19 -> 4
            20 -> 5
            else -> null
        }
    }

    fun mostrarPersonagem() {
        println("## $nome ##")
        println("Pontos de Vida: ${calculaPV()}")
        mostrarAtributos()
    }
}
