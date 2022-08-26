package id.ac.umn.tugasakhir_virtualinternship_rakaminacademy.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import id.ac.umn.tugasakhir_virtualinternship_rakaminacademy.data.BeritaRepository
import id.ac.umn.tugasakhir_virtualinternship_rakaminacademy.data.remote.response.BeritaResponse
import id.ac.umn.tugasakhir_virtualinternship_rakaminacademy.data.util.Resource
import kotlinx.coroutines.launch
import retrofit2.Response

class BeritaViewModel(
    val beritaRepository: BeritaRepository
) : ViewModel() {

    val berita : MutableLiveData<Resource<BeritaResponse>> = MutableLiveData()
    var beritaPage = 1

    init {
        getBerita("id")
    }

    fun getBerita(kodeNegara: String) = viewModelScope.launch {
        berita.postValue(Resource.Loading())

        val response = beritaRepository.getBerita(kodeNegara, beritaPage)
        berita.postValue(handleBeritaResponse(response))
    }

    // check respon berhasil atau tidak
    private fun handleBeritaResponse(response: Response<BeritaResponse>) : Resource<BeritaResponse>{
        if (response.isSuccessful){
            response.body()?.let {
                return Resource.Success(it)
            }
        }
        return Resource.Error(response.message())
    }
}