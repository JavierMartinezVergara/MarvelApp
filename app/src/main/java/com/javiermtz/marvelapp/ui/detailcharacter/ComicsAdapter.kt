package com.javiermtz.marvelapp.ui.detailcharacter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.javiermtz.marvelapp.databinding.ComicItemBinding
import com.javiermtz.marvelapp.model.responses.ResultsComics
import com.javiermtz.marvelapp.ui.detailcharacter.ComicsAdapter.ComicViewHolder
import com.javiermtz.marvelapp.util.Constans

class ComicsAdapter(private val onClickListener: OnClickListener) :
  ListAdapter<ResultsComics, ComicViewHolder>(MyDiffUtil) {

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
      Glide.with(binding.imageComic)
        .load(ResultsComics?.thumbnail?.path + Constans.MARVELDIMENSIONMEDIUM + ResultsComics?.thumbnail?.extension)
        .into(binding.imageComic)

    }
  }

  class OnClickListener(val clickListener: (result: ResultsComics) -> Unit) {
    fun onClick(result: ResultsComics) = clickListener(result)
  }
}




