package com.javiermtz.marvelapp.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.javiermtz.marvelapp.R
import com.javiermtz.marvelapp.databinding.FragmentCharacterDetailBinding
import com.javiermtz.marvelapp.databinding.FragmentCharactersBinding
import com.javiermtz.marvelapp.util.Constans
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class CharacterDetailFragment : Fragment() {

  private lateinit var binding: FragmentCharacterDetailBinding
  private val viewModel: CharacteresViewModel by viewModels()


  val args: CharacterDetailFragmentArgs by navArgs()
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

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
    Glide.with(binding.imageCharacter)
      .load(args.characterData.image)
      .into(binding.imageCharacter)
    binding.nameCharacter.text = args.characterData.name
    binding.descriptionCharacter.text = args.characterData.description
    binding.numComicsCharacter.text = requireContext().getString(R.string.num_comics, args.characterData.numComics.toString())
    binding.numSeriesCharacter.text = requireContext().getString(R.string.num_series, args.characterData.numSeries.toString())
  }

}
