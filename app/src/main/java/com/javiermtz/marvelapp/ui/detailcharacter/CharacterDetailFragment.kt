package com.javiermtz.marvelapp.ui.detailcharacter

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.javiermtz.marvelapp.R
import com.javiermtz.marvelapp.databinding.FragmentCharacterDetailBinding
import com.javiermtz.marvelapp.ui.ComicsViewModel
import com.javiermtz.marvelapp.ui.detailcharacter.ComicsAdapter.OnClickListener
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CharacterDetailFragment : Fragment() {

  private lateinit var binding: FragmentCharacterDetailBinding
  private val viewModel: ComicsViewModel by viewModels()
  private lateinit var adapter: ComicsAdapter

  val args: CharacterDetailFragmentArgs by navArgs()
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

  }

  override fun onCreateView(
    inflater: LayoutInflater, container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View {
    binding = FragmentCharacterDetailBinding.inflate(layoutInflater, container, false)
    observers()
    return binding.root
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    viewModel.getComics(args.characterData.id)
    Glide.with(binding.imageCharacter)
      .load(args.characterData.image)
      .into(binding.imageCharacter)
    binding.nameCharacter.text = args.characterData.name
    binding.descriptionCharacter.text = args.characterData.description
    binding.numComicsCharacter.text =
      requireContext().getString(R.string.num_comics, args.characterData.numComics.toString())
    binding.numSeriesCharacter.text =
      requireContext().getString(R.string.num_series, args.characterData.numSeries.toString())

    adapter = ComicsAdapter(OnClickListener { comic ->
    })

  }

  private fun observers() {
    viewModel.comics.observe(viewLifecycleOwner, {
      val data = it
      adapter.submitList(data)
      binding.recyclerViewComics.adapter = adapter
    })
  }

}
