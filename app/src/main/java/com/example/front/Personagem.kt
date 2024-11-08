package com.example.front

import Atributos.*
import android.os.Parcelable
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Entity(
    tableName = "personagem",
    foreignKeys = [
        ForeignKey(
            entity = Forca::class,
            parentColumns = ["ForcaId"],
            childColumns = ["forcaId"],
            onDelete = ForeignKey.CASCADE,
            onUpdate = ForeignKey.CASCADE
        ),
        ForeignKey(
            entity = Destreza::class,
            parentColumns = ["DestrezaId"],
            childColumns = ["destrezaId"],
            onDelete = ForeignKey.CASCADE,
            onUpdate = ForeignKey.CASCADE
        ),
        ForeignKey(
            entity = Constituicao::class,
            parentColumns = ["ConstituicaoId"],
            childColumns = ["constituicaoId"],
            onDelete = ForeignKey.CASCADE,
            onUpdate = ForeignKey.CASCADE
        ),
        ForeignKey(
            entity = Inteligencia::class,
            parentColumns = ["InteligenciaId"],
            childColumns = ["inteligenciaId"],
            onDelete = ForeignKey.CASCADE,
            onUpdate = ForeignKey.CASCADE
        ),
        ForeignKey(
            entity = Carisma::class,
            parentColumns = ["CarismaId"],
            childColumns = ["carismaId"],
            onDelete = ForeignKey.CASCADE,
            onUpdate = ForeignKey.CASCADE
        ),
        ForeignKey(
            entity = Sabedoria::class,
            parentColumns = ["SabedoriaId"],
            childColumns = ["sabedoriaId"],
            onDelete = ForeignKey.CASCADE,
            onUpdate = ForeignKey.CASCADE
        )
    ]
)
@Parcelize
data class Personagem(
    @PrimaryKey(autoGenerate = true) val PersonagemId: Int = 0,
    var nome: String,
    var forcaId: Int,
    var destrezaId: Int,
    var constituicaoId: Int,
    var inteligenciaId: Int,
    var carismaId: Int,
    var sabedoriaId: Int,
) : Parcelable {
}