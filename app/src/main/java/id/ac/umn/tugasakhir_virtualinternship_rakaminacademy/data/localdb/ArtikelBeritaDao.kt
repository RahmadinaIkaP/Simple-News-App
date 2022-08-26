package id.ac.umn.tugasakhir_virtualinternship_rakaminacademy.data.localdb

import androidx.lifecycle.LiveData
import androidx.room.*
import id.ac.umn.tugasakhir_virtualinternship_rakaminacademy.model.Article

@Dao
interface ArtikelBeritaDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateInsert(article: Article) : Long

    @Query("SELECT * FROM artikel")
    fun getArtikel() : LiveData<List<Article>>

    @Delete
    suspend fun hapusArtikel(article: Article)
}
