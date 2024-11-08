package Atributos

import android.os.Parcel
import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "sabedoria")
data class Sabedoria (
    @PrimaryKey(autoGenerate = true) val SabedoriaId: Int = 0,
    var att: Int = 8
) : Atributo, Parcelable {

    override fun GastarPontos(pontos: Int) : Int  {
        if(pontos in 8..15)
        {
            var pontosGastos: Int = 0;

            when (pontos) {
                8  -> pontosGastos = 0
                9  -> pontosGastos = 1
                10 -> pontosGastos = 2
                11 -> pontosGastos = 3
                12 -> pontosGastos = 4
                13 -> pontosGastos = 5
                14 -> pontosGastos = 7
                15 -> pontosGastos = 9
            }

            this.att = pontos
            return pontosGastos
        }
        else {
            throw IllegalArgumentException()
        }
    }

    override fun getValue(): Int {
        return this.att
    }

    override fun addRaca(value: Int) {
        if(value >= 3 || value <= -2) {throw IllegalArgumentException()}
        this.att += value
    }
}