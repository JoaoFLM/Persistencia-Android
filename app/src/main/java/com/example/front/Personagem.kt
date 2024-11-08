import Atributos.*
import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Entity(tableName = "personagem")
@Parcelize
data class Personagem(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    var Nome: String,
    var ForcaPJ: Forca,
    var DestrezaPJ: Destreza,
    var ConstituicaoPJ: Constituicao,
    var InteligenciaPJ: Inteligencia,
    var CarismaPJ: Carisma,
    var SabedoriaPJ: Sabedoria,
    ) : Parcelable {

    }