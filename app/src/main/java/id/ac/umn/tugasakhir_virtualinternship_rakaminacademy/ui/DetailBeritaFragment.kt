package id.ac.umn.tugasakhir_virtualinternship_rakaminacademy.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebViewClient
import androidx.navigation.fragment.navArgs
import id.ac.umn.tugasakhir_virtualinternship_rakaminacademy.MainActivity
import id.ac.umn.tugasakhir_virtualinternship_rakaminacademy.databinding.FragmentDetailBeritaBinding
import id.ac.umn.tugasakhir_virtualinternship_rakaminacademy.ui.viewmodel.BeritaViewModel


class DetailBeritaFragment : Fragment() {

    private var _binding: FragmentDetailBeritaBinding? = null
    private val binding get() = _binding!!
    private lateinit var beritaViewModel: BeritaViewModel
    private val args: DetailBeritaFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDetailBeritaBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        beritaViewModel = (activity as MainActivity).viewModel
        val artikel = args.articles

        binding.webView.apply {
            webViewClient = WebViewClient()
            artikel.url?.let { loadUrl(it) }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}