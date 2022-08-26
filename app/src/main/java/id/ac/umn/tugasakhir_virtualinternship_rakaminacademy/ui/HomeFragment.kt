package id.ac.umn.tugasakhir_virtualinternship_rakaminacademy.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import id.ac.umn.tugasakhir_virtualinternship_rakaminacademy.MainActivity
import id.ac.umn.tugasakhir_virtualinternship_rakaminacademy.R
import id.ac.umn.tugasakhir_virtualinternship_rakaminacademy.data.util.Resource
import id.ac.umn.tugasakhir_virtualinternship_rakaminacademy.databinding.FragmentHomeBinding
import id.ac.umn.tugasakhir_virtualinternship_rakaminacademy.model.Article
import id.ac.umn.tugasakhir_virtualinternship_rakaminacademy.ui.adapter.BeritaAdapter
import id.ac.umn.tugasakhir_virtualinternship_rakaminacademy.ui.viewmodel.BeritaViewModel

class HomeFragment : Fragment(), BeritaAdapter.BeritaAdapterInterface{

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private lateinit var beritaViewModel: BeritaViewModel
    lateinit var beritaAdapter: BeritaAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        beritaViewModel = (activity as MainActivity).viewModel
        beritaAdapter = BeritaAdapter(this)
        showBeritaRecyclerView()

        beritaViewModel.berita.observe(viewLifecycleOwner, Observer { response ->
            when(response){
                is Resource.Success -> {
                    hideProgressBar()
                    response.data?.let {
                        beritaAdapter.differ.submitList(it.articles)
                    }
                }
                is Resource.Error -> {
                    hideProgressBar()
                    response.message?.let {
                        Log.d("HomeFragment", "Error: $it")
                        Toast.makeText(context, "Error: $it", Toast.LENGTH_SHORT).show()
                    }
                }
                is Resource.Loading -> showProgressBar()
            }
        })
    }

    private fun showBeritaRecyclerView(){
        with(binding){
            rvBerita.apply {
                adapter = beritaAdapter
                layoutManager = LinearLayoutManager(activity)
            }
        }
    }

    private fun hideProgressBar(){
        with(binding){
            progressBar.visibility = View.INVISIBLE
        }
    }

    private fun showProgressBar(){
        with(binding){
            progressBar.visibility = View.VISIBLE
        }
    }

    override fun onItemClicked(article: Article) {
        val action = HomeFragmentDirections.actionNavigationHomeToDetailBeritaFragment(article)
        findNavController().navigate(action)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}