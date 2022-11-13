package com.javiermtz.marvelapp.presentation.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.javiermtz.marvelapp.R
import com.javiermtz.marvelapp.databinding.ComicItemBinding
import com.javiermtz.marvelapp.domain.models.ComicDTO
import com.javiermtz.marvelapp.presentation.home.ComicsAdapter.ComicViewHolder
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ComicsAdapter(private val onClickListener: (ComicDTO) -> Unit) :
  ListAdapter<ComicDTO, ComicViewHolder>(MyDiffUtil) {

  private val adapterScope = CoroutineScope(Dispatchers.Default)

  companion object MyDiffUtil : DiffUtil.ItemCallback<ComicDTO>() {
    override fun areItemsTheSame(oldItem: ComicDTO, newItem: ComicDTO): Boolean {
      return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: ComicDTO, newItem: ComicDTO): Boolean {
      return oldItem.id == newItem.id
    }
  }

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ComicViewHolder {
    return ComicViewHolder(
      ComicItemBinding.inflate(
        LayoutInflater.from(parent.context), parent, false
      )
    )
  }

  override fun onBindViewHolder(holder: ComicViewHolder, position: Int) {
    val result = getItem(position)

    holder.bind(result, onClickListener)
  }

  inner class ComicViewHolder(private val binding: ComicItemBinding) :
    RecyclerView.ViewHolder(binding.root) {
    fun bind(comic: ComicDTO, onClickListener: (ComicDTO) -> Unit) {
      binding.titleComic.text = comic.title
      adapterScope.launch {
        withContext(Dispatchers.Main) {
          Glide.with(binding.imageComic)
            .load(comic.image)
            .placeholder(R.drawable.splash_image)
            .error(R.drawable.splash_image)
            .into(binding.imageComic)
        }
      }
      binding.root.setOnClickListener {
        onClickListener(comic)
      }
    }

  }
}




