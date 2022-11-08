package com.javiermtz.marvelapp.presentation.characters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.javiermtz.marvelapp.databinding.CharacterItemBinding
import com.javiermtz.marvelapp.model.responses.Results
import com.javiermtz.marvelapp.presentation.characters.CharacterAdapter.ViewHolder
import com.javiermtz.marvelapp.util.Constans

class CharacterAdapter(
  private val onClickListener: OnClickListener,
  private val onClickListener2: OnClickListener2
) :
  ListAdapter<Results, ViewHolder>(MyDiffUtil) {

  companion object MyDiffUtil : DiffUtil.ItemCallback<Results>() {
    override fun areItemsTheSame(oldItem: Results, newItem: Results): Boolean {
      return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: Results, newItem: Results): Boolean {
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
      onClickListener.onClick(result)
    }
    if (position == currentList.size - 1) {
      onClickListener2.onClick2()
    }
    holder.bind(result)
  }

  inner class ViewHolder(private val binding: CharacterItemBinding) :
    RecyclerView.ViewHolder(binding.root) {
    fun bind(results: Results?) {
      binding.name.text = results?.name
      Glide.with(binding.imageCharacter)
        .load(results?.thumbnail?.path + Constans.MARVELDIMENSIONMEDIUM + results?.thumbnail?.extension)
        .into(binding.imageCharacter)

    }
  }

  class OnClickListener(val clickListener: (result: Results) -> Unit) {
    fun onClick(result: Results) = clickListener(result)
  }

  class OnClickListener2(val clickListener2: () -> Unit) {
    fun onClick2() = clickListener2()
  }
}




