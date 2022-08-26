package id.ac.umn.tugasakhir_virtualinternship_rakaminacademy

import android.os.Bundle
import android.view.View
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import id.ac.umn.tugasakhir_virtualinternship_rakaminacademy.data.BeritaRepository
import id.ac.umn.tugasakhir_virtualinternship_rakaminacademy.data.BeritaViewModelProviderFactory
import id.ac.umn.tugasakhir_virtualinternship_rakaminacademy.data.localdb.ArtikelDatabase
import id.ac.umn.tugasakhir_virtualinternship_rakaminacademy.databinding.ActivityMainBinding
import id.ac.umn.tugasakhir_virtualinternship_rakaminacademy.ui.viewmodel.BeritaViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    lateinit var viewModel : BeritaViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // inisialisasi viewModel dari berita
        val repository = BeritaRepository(ArtikelDatabase(this))
        val viewModelProviderFactory = BeritaViewModelProviderFactory(repository)
        viewModel = ViewModelProvider(this, viewModelProviderFactory).get(BeritaViewModel::class.java)

        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment_activity_main) as NavHostFragment
        val navController = navHostFragment.navController

        // Jika destinasinya sama seperti id di menu, munculkan bottomNav
        navController.addOnDestinationChangedListener{_, destination, _ ->
            when(destination.id){
                R.id.navigation_home -> showBottomNav()
                else -> hideBottomNav()
            }
        }
        binding.navView.setupWithNavController(navController)
    }

    private fun showBottomNav() {
        binding.navView.visibility = View.VISIBLE
    }

    private fun hideBottomNav() {
        binding.navView.visibility = View.GONE
    }
}