package com.javiermtz.marvelapp.presentation.detailcharacter

import android.content.Intent
import android.net.Uri
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
import com.javiermtz.marvelapp.model.responses.ResultsComics
import com.javiermtz.marvelapp.presentation.comics.ComicsViewModel
import com.javiermtz.marvelapp.presentation.detailcharacter.ComicsAdapter.OnClickListener
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CharacterDetailFragment : Fragment() {

  private lateinit var binding: FragmentCharacterDetailBinding
  private val viewModel: ComicsViewModel by viewModels()
  private lateinit var adapter: ComicsAdapter

  private val datos = mutableListOf<ResultsComics>()

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
    Glide.with(requireContext())
      .load(args.characterData.image)
      .placeholder(R.drawable.splash_image)
      .error(R.drawable.splash_image)
      .into(binding.imageCharacter)
    binding.imageCharacter.setOnClickListener {
      val intent : Intent = Intent(Intent.ACTION_VIEW, Uri.parse(args.characterData.url))
      startActivity(intent)
    }
    binding.nameCharacter.text = args.characterData.name
    binding.descriptionCharacter.text = args.characterData.description
    binding.numComicsCharacter.text =
      requireContext().getString(R.string.num_comics, args.characterData.numComics.toString())
    binding.numSeriesCharacter.text =
      requireContext().getString(R.string.num_series, args.characterData.numSeries.toString())

    adapter = ComicsAdapter(OnClickListener { comic ->
      val intent : Intent = Uri.parse(comic.urls.first().url).let {
        Intent(Intent.ACTION_VIEW, it)
      }
      startActivity(intent)
    })

  }

  private fun observers() {
    viewModel.comics.observe(viewLifecycleOwner, {
      val data = it
      adapter.submitList(data)
      binding.recyclerViewComics.adapter = adapter

    })
    viewModel.loading.observe(viewLifecycleOwner, {
      if (it){
        binding.imgLoading.visibility = View.VISIBLE
        binding.imgError.visibility = View.GONE
      } else binding.imgLoading.visibility = View.GONE
    })
    viewModel.error.observe(viewLifecycleOwner, {
      if (!it.isNullOrEmpty()){
        binding.imgError.visibility = View.VISIBLE
      } else{
        binding.imgError.visibility = View.GONE
      }
    })
  }

}
