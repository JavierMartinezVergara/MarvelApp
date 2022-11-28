package com.javiermtz.marvelapp.presentation.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.shared.models.ComicDTO
import com.javiermtz.marvelapp.R
import com.javiermtz.marvelapp.databinding.CharacterHomeItemBinding

class ComicsHomeAdapter(
  private val onClickListener: (ComicDTO) -> Unit
) :
  ListAdapter<ComicDTO, ComicsViewHolder>(DiffUtilItem) {

  companion object DiffUtilItem : DiffUtil.ItemCallback<ComicDTO>() {
    override fun areItemsTheSame(oldItem: ComicDTO, newItem: ComicDTO): Boolean {
      return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: ComicDTO, newItem: ComicDTO): Boolean {
      return oldItem.id == newItem.id
    }
  }

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ComicsViewHolder {
    val layoutInflater = LayoutInflater.from(parent.context)
    return ComicsViewHolder(
      layoutInflater.inflate(
        R.layout.character_home_item, parent, false
      )
    )
  }

  override fun onBindViewHolder(holder: ComicsViewHolder, position: Int) {
    val item = getItem(position)
    holder.bind(item, onClickListener)
  }

}

class ComicsViewHolder(view: View) : RecyclerView.ViewHolder(view) {
  private val binding = CharacterHomeItemBinding.bind(view)

  fun bind(item: ComicDTO, onClickListener: (ComicDTO) -> Unit) {
    binding.apply {
      nameText.text = item.title
      imageCharacter.load(item.image)

    }

    binding.root.setOnClickListener {
      onClickListener(item)
    }

  }
}
