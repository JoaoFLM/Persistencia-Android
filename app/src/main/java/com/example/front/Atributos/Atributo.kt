package Atributos

import android.os.Parcelable

interface Atributo : Parcelable {
    fun GastarPontos(pontos: Int) : Int
    fun getValue() : Int
    fun addRaca(value: Int)
}

