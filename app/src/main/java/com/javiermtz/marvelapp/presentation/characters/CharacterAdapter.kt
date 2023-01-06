package com.javiermtz.marvelapp.presentation.characters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.shared.models.SerieDTO
import com.javiermtz.marvelapp.databinding.CharacterItemBinding
import com.javiermtz.marvelapp.presentation.characters.CharacterAdapter.ViewHolder

class CharacterAdapter(
  val onCLickLister: (SerieDTO) -> Unit
) :
  ListAdapter<SerieDTO, ViewHolder>(MyDiffUtil) {

  companion object MyDiffUtil : DiffUtil.ItemCallback<SerieDTO>() {
    override fun areItemsTheSame(oldItem: SerieDTO, newItem: SerieDTO): Boolean {
      return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: SerieDTO, newItem: SerieDTO): Boolean {
      return oldItem.id == newItem.id
    }
  }

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
    return ViewHolder(
      CharacterItemBinding.inflate(
        LayoutInflater.from(parent.context), parent, false
      )
    )
  }

  override fun onBindViewHolder(holder: ViewHolder, position: Int) {
    val result = getItem(position)
    holder.itemView.setOnClickListener {
    }
    holder.bind(result, onCLickLister)
  }

  inner class ViewHolder(private val binding: CharacterItemBinding) :
    RecyclerView.ViewHolder(binding.root) {
    fun bind(charactersMarvel: SerieDTO, onCLickLister: (SerieDTO) -> Unit) {
      binding.tvName.text = charactersMarvel.name
      binding.imgCharacter.toLoad(charactersMarvel.image)

      binding.root.setOnClickListener {
        onCLickLister(charactersMarvel)
      }
    }
  }

}




