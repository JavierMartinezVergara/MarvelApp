package com.javiermtz.marvelapp.presentation.detailcharacter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.javiermtz.marvelapp.R
import com.javiermtz.marvelapp.databinding.ComicItemBinding
import com.javiermtz.marvelapp.model.responses.ResultsComics
import com.javiermtz.marvelapp.presentation.detailcharacter.ComicsAdapter.ComicViewHolder
import com.javiermtz.marvelapp.util.Constans
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ComicsAdapter(private val onClickListener: OnClickListener) :
  ListAdapter<ResultsComics, ComicViewHolder>(MyDiffUtil) {

  private val adapterScope = CoroutineScope(Dispatchers.Default)


  companion object MyDiffUtil : DiffUtil.ItemCallback<ResultsComics>() {
    override fun areItemsTheSame(oldItem: ResultsComics, newItem: ResultsComics): Boolean {
      return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: ResultsComics, newItem: ResultsComics): Boolean {
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
    holder.itemView.setOnClickListener {
      onClickListener.onClick(result)
    }
    holder.bind(result)
  }

  inner class ComicViewHolder(private val binding: ComicItemBinding) :
    RecyclerView.ViewHolder(binding.root) {
    fun bind(ResultsComics: ResultsComics?) {
      binding.nameComic.text = ResultsComics?.title
      adapterScope.launch {
        withContext(Dispatchers.Main){
          Glide.with(binding.imageComic)
            .load(ResultsComics?.thumbnail?.path + Constans.MARVELDIMENSIONMEDIUM + ResultsComics?.thumbnail?.extension)
            .placeholder(R.drawable.splash_image)
            .error(R.drawable.splash_image)
            .into(binding.imageComic)
        }
      }
      }

  }

  class OnClickListener(val clickListener: (result: ResultsComics) -> Unit) {
    fun onClick(result: ResultsComics) = clickListener(result)
  }
}




