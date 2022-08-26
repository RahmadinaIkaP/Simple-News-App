package id.ac.umn.tugasakhir_virtualinternship_rakaminacademy.data

import id.ac.umn.tugasakhir_virtualinternship_rakaminacademy.data.localdb.ArtikelDatabase
import id.ac.umn.tugasakhir_virtualinternship_rakaminacademy.data.remote.api.NetworkModule

class BeritaRepository(
    val db : ArtikelDatabase
) {
    suspend fun getBerita(kodeNegara: String, jmlPage: Int) =
        NetworkModule.api.getBerita(kodeNegara,jmlPage)
}