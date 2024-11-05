import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.front.DataBase.CarismaDAO
import com.example.front.DataBase.ConstituicaoDAO
import com.example.front.DataBase.ForcaDAO
import com.example.front.DataBase.InteligenciaDAO
import com.example.front.DataBase.PersonagemDAO
import com.example.front.DataBase.SabedoriaDAO
import com.example.front.Personagem
import Atributos.*
import android.content.Context
import android.util.Log

@Database(entities = [Personagem::class, Carisma::class, Constituicao::class, Destreza::class, Forca::class, Inteligencia::class, Sabedoria::class], version = 1, exportSchema = false)
abstract class AppDataBase : RoomDatabase() {

    abstract fun personagemDAO(): PersonagemDAO
    abstract fun CarismaDAO(): CarismaDAO
    abstract fun ConstituicaoDAO(): ConstituicaoDAO
    abstract fun DestrezaDAO(): DestrezaDAO
    abstract fun ForcaDAO(): ForcaDAO
    abstract fun InteligenciaDAO(): InteligenciaDAO
    abstract fun SabedoriaDAO(): SabedoriaDAO

    companion object {

        @Volatile
        private var INSTANCE: AppDataBase? = null

        fun getDatabase(context: Context): AppDataBase {
            var instance = INSTANCE
            if(instance == null) {
                instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDataBase::class.java,
                    "dnd_db"
                ).fallbackToDestructiveMigrationFrom()
                    .build()
                INSTANCE = instance
            }
            return instance
//            return INSTANCE ?: synchronized(this) {
//                val instance = Room.databaseBuilder(
//                    context.applicationContext,
//                    AppDataBase::class.java,
//                    "app_database"
//                ).fallbackToDestructiveMigration().build()
//                INSTANCE = instance
//                if(instance != null){
//                    Log.e("Erro", "Instance nula")
//                }
//                if(INSTANCE != null){
//                    Log.e("Erro", "INSTANCIA nula")
//                }
//                instance
//            }
        }
    }
}
