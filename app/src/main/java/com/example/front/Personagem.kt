import Atributos.*
import android.os.Parcel
import android.os.Parcelable


class Personagem(
    var Nome: String,
    var ForcaPJ: Forca,
    var DestrezaPJ: Destreza,
    var ConstituicaoPJ: Constituicao,
    var InteligenciaPJ: Inteligencia,
    var CarismaPJ: Carisma,
    var SabedoriaPJ: Sabedoria,
    //var Raca: Raca
    ) : Parcelable
    {
        constructor(parcel: Parcel) : this(
        parcel.readString() ?: "",
        parcel.readParcelable(Forca::class.java.classLoader) ?: Forca(),
        parcel.readParcelable(Destreza::class.java.classLoader) ?: Destreza(),
        parcel.readParcelable(Constituicao::class.java.classLoader) ?: Constituicao(),
        parcel.readParcelable(Inteligencia::class.java.classLoader) ?: Inteligencia(),
        parcel.readParcelable(Carisma::class.java.classLoader) ?: Carisma(),
        parcel.readParcelable(Sabedoria::class.java.classLoader) ?: Sabedoria()
        )

        override fun writeToParcel(parcel: Parcel, flags: Int) {
            parcel.writeString(Nome)
            parcel.writeParcelable(ForcaPJ, flags)
            parcel.writeParcelable(DestrezaPJ, flags)
            parcel.writeParcelable(ConstituicaoPJ, flags)
            parcel.writeParcelable(InteligenciaPJ, flags)
            parcel.writeParcelable(CarismaPJ, flags)
            parcel.writeParcelable(SabedoriaPJ, flags)
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