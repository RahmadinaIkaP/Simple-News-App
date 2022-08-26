package id.ac.umn.tugasakhir_virtualinternship_rakaminacademy.data

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import id.ac.umn.tugasakhir_virtualinternship_rakaminacademy.ui.viewmodel.BeritaViewModel

class BeritaViewModelProviderFactory(
    val beritaRepository: BeritaRepository
) : ViewModelProvider.Factory{

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return BeritaViewModel(beritaRepository) as T
    }

}