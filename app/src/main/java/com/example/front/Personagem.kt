package com.example.front

import Atributos.*
import android.os.Parcel
import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.ForeignKey

@Entity(tableName = "personagem",
    foreignKeys = [
        ForeignKey(
            entity = Forca::class,
            parentColumns = arrayOf("id"),
            childColumns = arrayOf("forcaId"),
            onDelete = ForeignKey.CASCADE
        ),
        ForeignKey(
            entity = Destreza::class,
            parentColumns = arrayOf("id"),
            childColumns = arrayOf("destrezaId"),
            onDelete = ForeignKey.CASCADE
        ),
        ForeignKey(
            entity = Constituicao::class,
            parentColumns = arrayOf("id"),
            childColumns = arrayOf("constituicaoId"),
            onDelete = ForeignKey.CASCADE
        ),
        ForeignKey(
            entity = Inteligencia::class,
            parentColumns = arrayOf("id"),
            childColumns = arrayOf("inteligenciaId"),
            onDelete = ForeignKey.CASCADE
        ),
        ForeignKey(
            entity = Carisma::class,
            parentColumns = arrayOf("id"),
            childColumns = arrayOf("carismaId"),
            onDelete = ForeignKey.CASCADE
        ),
        ForeignKey(
            entity = Sabedoria::class,
            parentColumns = arrayOf("id"),
            childColumns = arrayOf("sabedoriaId"),
            onDelete = ForeignKey.CASCADE
        )
    ])
data class Personagem(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    var nome: String,
    var forcaId: Int,
    var destrezaId: Int,
    var constituicaoId: Int,
    var inteligenciaId: Int,
    var carismaId: Int,
    var sabedoriaId: Int
) : Parcelable {

    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readString() ?: "",
        parcel.readInt(),
        parcel.readInt(),
        parcel.readInt(),
        parcel.readInt(),
        parcel.readInt(),
        parcel.readInt()
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(id)
        parcel.writeString(nome)
        parcel.writeInt(forcaId)
        parcel.writeInt(destrezaId)
        parcel.writeInt(constituicaoId)
        parcel.writeInt(inteligenciaId)
        parcel.writeInt(carismaId)
        parcel.writeInt(sabedoriaId)
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

    fun calculaPV(): Int? {
        val pvBase = 10
        return when (constituicaoId) {
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

    private fun calculaMod(atributoId: Int): Int? {
        return when (atributoId) {
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
}
