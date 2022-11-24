package com.javiermtz.marvelapp.presentation.detailcharacter

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.javiermtz.marvelapp.R
import com.javiermtz.marvelapp.databinding.FragmentCharacterDetailBinding
import com.javiermtz.marvelapp.presentation.comics.ComicsViewModel
import com.javiermtz.marvelapp.presentation.home.ComicsAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class CharacterDetailFragment : Fragment() {

  private lateinit var binding: FragmentCharacterDetailBinding
  private val viewModel: ComicsViewModel by viewModels()
  private lateinit var adapter: ComicsAdapter

  //private val datos = mutableListOf<ComicsD>()

  val args: CharacterDetailFragmentArgs by navArgs()
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    viewModel.getComics(args.characterData.id)
  }

  override fun onCreateView(
    inflater: LayoutInflater, container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View {
    binding = FragmentCharacterDetailBinding.inflate(layoutInflater, container, false)
    return binding.root
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    observers()
    Glide.with(requireContext())
      .load(args.characterData.image)
      .placeholder(R.drawable.splash_image)
      .error(R.drawable.splash_image)
      .into(binding.imageCharacter)
    binding.imageCharacter.setOnClickListener {
      //val intent : Intent = Intent(Intent.ACTION_VIEW, Uri.parse(args.characterData.url))
      //startActivity(intent)
    }
    //binding.nameCharacter.text = args.characterData.name
    binding.descriptionCharacter.text = args.characterData.description
    binding.tvComicsAppearance.text =
      requireContext().getString(R.string.num_comics)


    adapter = ComicsAdapter { comic ->
      //val intent : Intent = Uri.parse(comic.first().url).let {
      //Intent(Intent.ACTION_VIEW, it)
      //}
      //startActivity(intent)
    }
    binding.recyclerViewComics.layoutManager =
      LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
    binding.recyclerViewComics.adapter = adapter

  }

  private fun observers() {
    viewLifecycleOwner.lifecycleScope.launch {
      viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
        launch {
          viewModel.comics.collectLatest {
            adapter.submitList(it)
          }
        }
      }
    }

  }

}
