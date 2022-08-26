package id.ac.umn.tugasakhir_virtualinternship_rakaminacademy.data.localdb

import android.content.Context
import androidx.room.*
import id.ac.umn.tugasakhir_virtualinternship_rakaminacademy.model.Article

@Database(
    entities = [Article::class],
    version = 1
)
@TypeConverters(Converter::class)
abstract class ArtikelDatabase : RoomDatabase(){

    abstract fun getArtikelDao() : ArtikelBeritaDao

    companion object{
        @Volatile
        private var instance : ArtikelDatabase? = null
        private val LOCK = Any()

        operator fun invoke(context: Context) = instance?: synchronized(LOCK){
            instance?:createDatabase(context).also{ instance = it}
        }

        private fun createDatabase(context: Context) =
            Room.databaseBuilder(
                context.applicationContext,
                ArtikelDatabase::class.java,
                "artikel_db.db"
            ).build()
    }
}