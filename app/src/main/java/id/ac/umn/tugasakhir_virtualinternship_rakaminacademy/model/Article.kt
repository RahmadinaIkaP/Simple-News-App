package id.ac.umn.tugasakhir_virtualinternship_rakaminacademy.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(
    tableName = "artikel"
)
data class Article(
    @PrimaryKey(autoGenerate = true)
    var articleId: Int? = null,
    val author: String?,
    val content: String?,
    val description: String?,
    val publishedAt: String?,
    val source: Source?,
    val title: String?,
    val url: String?,
    val urlToImage: String?
) : Serializable