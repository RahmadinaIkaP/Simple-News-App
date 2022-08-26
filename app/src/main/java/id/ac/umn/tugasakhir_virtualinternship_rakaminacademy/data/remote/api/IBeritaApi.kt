package id.ac.umn.tugasakhir_virtualinternship_rakaminacademy.data.remote.api

import id.ac.umn.tugasakhir_virtualinternship_rakaminacademy.data.remote.response.BeritaResponse
import id.ac.umn.tugasakhir_virtualinternship_rakaminacademy.data.util.Constant.Companion.API_KEY
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface IBeritaApi {

    @GET("v2/top-headlines")
    suspend fun getBerita(
        @Query("country")
        kodeNegara : String = "id",
        @Query("page")
        jumlahPage : Int = 1,
        @Query("apiKey")
        apiKey : String = API_KEY
    ) : Response<BeritaResponse>

}