package com.javiermtz.marvelapp.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.javiermtz.marvelapp.databinding.CharacterItemBinding
import com.javiermtz.marvelapp.model.responses.Results
import com.javiermtz.marvelapp.util.Constans

class CharacterAdapter(private val onClickListener: OnClickListener) :
    ListAdapter<Results, CharacterAdapter.ViewHolder>(MyDiffUtil) {

    companion object MyDiffUtil : DiffUtil.ItemCallback<Results>() {
        override fun areItemsTheSame(oldItem: Results, newItem: Results): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: Results, newItem: Results): Boolean {
            return oldItem.id == newItem.id
        }
    }



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterAdapter.ViewHolder {
        return ViewHolder(CharacterItemBinding.inflate(
            LayoutInflater.from(parent.context),parent, false
        ))
    }

    override fun onBindViewHolder(holder: CharacterAdapter.ViewHolder, position: Int) {
        val result = getItem(position)
        holder.itemView.setOnClickListener {
            onClickListener.onClick(result)
        }
        holder.bind(result)
    }
    inner class ViewHolder(private val binding: CharacterItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(results: Results?) {
            binding.name.text = results?.name
            Glide.with(binding.imageCharacter)
                .load(results?.thumbnail?.path+Constans.MARVELDIMENSIONMEDIUM+results?.thumbnail?.extension)
                .into(binding.imageCharacter)

        }
    }

    class OnClickListener(val clickListener: (result : Results) -> Unit) {
        fun onClick(result: Results) = clickListener(result)
    }
}




