import Atributos.Destreza
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface DestrezaDAO {

    @Insert
    suspend fun insertDestreza(atributo: Destreza)

    @Delete
    suspend fun removeDestreza(atributo: Destreza)


    @Query("SELECT * FROM destreza")
    suspend fun selectAll(): List<Destreza>
}