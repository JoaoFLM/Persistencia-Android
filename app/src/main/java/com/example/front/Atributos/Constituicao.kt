package Atributos

import android.os.Parcel
import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName="constituição")
data class Constituicao (@PrimaryKey(autoGenerate = true) var id:Int = 0, var att: Int = 8) : Atributo  {
    constructor(parcel: Parcel) : this(parcel.readInt())


    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(att)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Constituicao> {
        override fun createFromParcel(parcel: Parcel): Constituicao {
            return Constituicao(parcel)
        }

        override fun newArray(size: Int): Array<Constituicao?> {
            return arrayOfNulls(size)
        }
    }

    override fun GastarPontos(pontos: Int) : Int {
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

            att = pontos
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