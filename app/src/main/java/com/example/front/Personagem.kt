package com.example.front

import Atributos.*
import android.os.Parcelable
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Entity(tableName = "personagem",)
@Parcelize
data class Personagem(
    @PrimaryKey(autoGenerate = true) val PersonagemId: Int = 0,
    var nome: String,
    var forca: Int,
    var destreza: Int,
    var constituicao: Int,
    var inteligencia: Int,
    var carisma: Int,
    var sabedoria: Int,
) : Parcelable {
}