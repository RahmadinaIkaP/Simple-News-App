package id.ac.umn.tugasakhir_virtualinternship_rakaminacademy.data.remote.response

import id.ac.umn.tugasakhir_virtualinternship_rakaminacademy.model.Article

data class BeritaResponse(
    val articles: List<Article>,
    val status: String,
    val totalResults: Int
)