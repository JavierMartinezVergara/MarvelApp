package com.javiermtz.marvelapp.presentation.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.shared.models.CharactersMarvel
import com.javiermtz.marvelapp.R
import com.javiermtz.marvelapp.databinding.CharacterHomeItemBinding

class CharactersHomeAdapter(
  private val characterListener: (CharactersMarvel) -> Unit
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
    holder.bind(item, characterListener)
  }

}

class CharactersViewHolder(view: View) : RecyclerView.ViewHolder(view) {
  private val binding = CharacterHomeItemBinding.bind(view)

  fun bind(item: CharactersMarvel, characterListener: (CharactersMarvel) -> Unit) {
    binding.apply {
      nameText.text = item.name
      imageCharacter.load(item.image)

    }

    binding.root.setOnClickListener {
      characterListener(item)
    }

  }
}
