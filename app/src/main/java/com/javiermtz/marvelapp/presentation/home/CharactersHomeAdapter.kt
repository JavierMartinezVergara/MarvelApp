package com.javiermtz.marvelapp.presentation.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.javiermtz.marvelapp.R
import com.javiermtz.marvelapp.databinding.CharacterHomeItemBinding
import com.javiermtz.marvelapp.databinding.CharacterItemBinding
import com.javiermtz.marvelapp.domain.models.CharactersMarvel
import com.javiermtz.marvelapp.presentation.characters.CharacterAdapter.ViewHolder

class CharactersHomeAdapter(
) :
  ListAdapter<CharactersMarvel, CharactersViewHolder>(DiffUtilItem) {

  companion object DiffUtilItem : DiffUtil.ItemCallback<CharactersMarvel>() {
    override fun areItemsTheSame(oldItem: CharactersMarvel, newItem: CharactersMarvel): Boolean {
      return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: CharactersMarvel, newItem: CharactersMarvel): Boolean {
      return oldItem.id == newItem.id
    }
  }

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharactersViewHolder {
    val layoutInflater = LayoutInflater.from(parent.context)
    return CharactersViewHolder(
      layoutInflater.inflate(
        R.layout.character_home_item, parent, false
      )
    )
  }

  override fun onBindViewHolder(holder: CharactersViewHolder, position: Int) {
    val item = getItem(position)
    holder.bind(item)
  }

}

class CharactersViewHolder(view: View) : RecyclerView.ViewHolder(view) {
  private val binding = CharacterHomeItemBinding.bind(view)

  fun bind(item: CharactersMarvel) {
    binding.apply {
      nameText.text = item.name
      imageCharacter.load(item.image)

    }

  }
}
