package id.ac.umn.tugasakhir_virtualinternship_rakaminacademy.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import id.ac.umn.tugasakhir_virtualinternship_rakaminacademy.databinding.ItemBeritaBinding
import id.ac.umn.tugasakhir_virtualinternship_rakaminacademy.model.Article

class BeritaAdapter(private val onClicCallback: BeritaAdapter.BeritaAdapterInterface) : RecyclerView.Adapter<BeritaAdapter.ArtikelViewHolder>() {

    private val differCallback = object : DiffUtil.ItemCallback<Article>(){
        override fun areItemsTheSame(oldItem: Article, newItem: Article): Boolean {
            return oldItem.url == newItem.url
        }

        override fun areContentsTheSame(oldItem: Article, newItem: Article): Boolean {
            return oldItem == newItem
        }

    }

    val differ = AsyncListDiffer(this, differCallback)

    inner class ArtikelViewHolder(private val binding : ItemBeritaBinding) :
        RecyclerView.ViewHolder(binding.root){
            fun bind(article: Article){
                with(binding){
                    Glide.with(itemView)
                        .load(article.urlToImage)
                        .into(ivArtikel)

                    tvSumber.text = article.source?.name
                    tvJudul.text = article.title
                    tvTglPublish.text = article.publishedAt

                    itemView.setOnClickListener {
                        onClicCallback.onItemClicked(article)
                    }
                }
            }
    }

    interface BeritaAdapterInterface {
        fun onItemClicked(article: Article)
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArtikelViewHolder {
        val binding = ItemBeritaBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ArtikelViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ArtikelViewHolder, position: Int) {
        val artikel = differ.currentList[position]
        holder.bind(artikel)
    }

    override fun getItemCount() : Int = differ.currentList.size
}