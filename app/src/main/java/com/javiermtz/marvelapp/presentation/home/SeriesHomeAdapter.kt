package com.javiermtz.marvelapp.presentation.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.shared.models.SerieDTO
import com.javiermtz.marvelapp.R
import com.javiermtz.marvelapp.databinding.ComicItemBinding
import com.javiermtz.marvelapp.presentation.home.SeriesHomeAdapter.SeriesViewHolder
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class SeriesHomeAdapter(private val onClickListener: (SerieDTO) -> Unit) :
  ListAdapter<SerieDTO, SeriesViewHolder>(MyDiffUtil) {

  private val adapterScope = CoroutineScope(Dispatchers.Default)

  companion object MyDiffUtil : DiffUtil.ItemCallback<SerieDTO>() {
    override fun areItemsTheSame(oldItem: SerieDTO, newItem: SerieDTO): Boolean {
      return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: SerieDTO, newItem: SerieDTO): Boolean {
      return oldItem.id == newItem.id
    }
  }

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SeriesViewHolder {
    return SeriesViewHolder(
      ComicItemBinding.inflate(
        LayoutInflater.from(parent.context), parent, false
      )
    )
  }

  override fun onBindViewHolder(holder: SeriesViewHolder, position: Int) {
    val result = getItem(position)

    holder.bind(result, onClickListener)
  }

  inner class SeriesViewHolder(private val binding: ComicItemBinding) :
    RecyclerView.ViewHolder(binding.root) {
    fun bind(serie: SerieDTO, onClickListener: (SerieDTO) -> Unit) {
      binding.titleComic.text = serie.title
      adapterScope.launch {
        withContext(Dispatchers.Main) {
          Glide.with(binding.imageComic)
            .load(serie.image)
            .placeholder(R.drawable.splash_image)
            .error(R.drawable.splash_image)
            .into(binding.imageComic)
        }
      }
      binding.root.setOnClickListener {
        onClickListener(serie)
      }
    }

  }
}




