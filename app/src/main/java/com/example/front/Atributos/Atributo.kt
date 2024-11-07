package Atributos

import android.os.Parcelable


interface Atributo {
    fun GastarPontos(pontos: Int) : Int
    fun getValue() : Int
    fun addRaca(value: Int)
}

