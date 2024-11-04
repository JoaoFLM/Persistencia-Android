package Atributos

import android.os.Parcel
import android.os.Parcelable

class Carisma(var att: Int = 8) : Atributo {
    constructor(parcel: Parcel) : this(parcel.readInt())

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(att)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Carisma> {
        override fun createFromParcel(parcel: Parcel): Carisma {
            return Carisma(parcel)
        }

        override fun newArray(size: Int): Array<Carisma?> {
            return arrayOfNulls(size)
        }
    }

    override fun GastarPontos(pontos: Int) : Int {
        if (pontos in 8..15) {
            var pontosGastos: Int = 0;

            when (pontos) {
                8 -> pontosGastos = 0
                9 -> pontosGastos = 1
                10 -> pontosGastos = 2
                11 -> pontosGastos = 3
                12 -> pontosGastos = 4
                13 -> pontosGastos = 5
                14 -> pontosGastos = 7
                15 -> pontosGastos = 9
            }
            this.att = pontos
            return pontosGastos
        } else {
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